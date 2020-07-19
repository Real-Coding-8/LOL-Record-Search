package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.api.GameIdApiClient;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.repository.GameIdSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameIdSearchService {

    @Autowired
    private GameIdApiClient gameIdApiClient;
    @Autowired
    private GameIdSearchRepository gameIdSearchRepository;


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
