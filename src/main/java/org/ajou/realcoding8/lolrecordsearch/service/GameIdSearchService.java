package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.api.GameIdApiClient;
import org.ajou.realcoding8.lolrecordsearch.domain.Details;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.domain.Match;
import org.ajou.realcoding8.lolrecordsearch.repository.GameIdSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.soap.Detail;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameIdSearchService {

    @Autowired
    private  GameIdApiClient gameIdApiClient;
    @Autowired
    private GameIdSearchRepository gameIdSearchRepository;

    @GetMapping("/lolrecordsearch/gameidsearch")
    public List<Long> getGameIds(String encryptedAccountId, String apiKey){
        Match match = new Match();
        if((match = gameIdSearchRepository.findCurrentWeather(encryptedAccountId))!=null) {
            log.info("find in DB");
        }
        else {
            match = gameIdApiClient.getMatches(encryptedAccountId, apiKey);
            gameIdSearchRepository.saveMatches(match);
            log.info("save new");
        }

        return match.getMatches().stream()
                .map(match_ -> match_.getGameId())
                .collect(Collectors.toList());

    }

    public Details getGameDetails(String matchId, String apiKey) {
        Details details = new Details();
        if((details = gameIdSearchRepository.findGameDetails(matchId)) != null) {
            log.info("find in DB");
            return details;
        }
        else {
            details =gameIdApiClient.getGameDetails(matchId, apiKey);
            gameIdSearchRepository.saveGameDetails(details);
            return details;
        }
    }

    public Info getUserInfo(String summonerName, String apiKey) {
        Info info = new Info();
        if((info = gameIdSearchRepository.findUserInfo(summonerName)) != null) {
            log.info("find in DB");
            return info;
        }
        else {
            info =gameIdApiClient.getUserInfo(summonerName, apiKey);
            gameIdSearchRepository.saveUserInfo(info);
            return info;
        }

    }
}
