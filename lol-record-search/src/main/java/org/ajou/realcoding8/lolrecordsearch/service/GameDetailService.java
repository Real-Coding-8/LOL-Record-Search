package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.api.GameDetailApiClient;
import org.ajou.realcoding8.lolrecordsearch.domain.Detail;
import org.ajou.realcoding8.lolrecordsearch.repository.GameDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameDetailService {

    @Autowired
    private GameDetailApiClient gameDetailApiClient;

    @Autowired
    private GameDetailRepository gameDetailRepository;


    public Detail getGameDetails(long matchId, String apiKey) {
        Detail detail = new Detail();

        if((detail = gameDetailRepository.findGameDetail(matchId)) != null) {
            log.info("find in DB");
        }
        else {
            detail = gameDetailApiClient.getGameDetail(matchId, apiKey);
            gameDetailRepository.saveGameDetail(detail);
            log.info("save new");
        }
        return detail;
    }
}
