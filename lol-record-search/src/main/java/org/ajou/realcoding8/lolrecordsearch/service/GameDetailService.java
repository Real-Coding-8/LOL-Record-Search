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


    public Detail getGameDetails(long gameId, String apiKey) {
        Detail detail = new Detail();
        detail = gameDetailRepository.findGameDetail(gameId);

        if(detail != null) {
            log.info("find GameDetail in DB");
        }
        else {
            detail = gameDetailApiClient.getGameDetail(gameId, apiKey);
            gameDetailRepository.saveGameDetail(detail);
            log.info("save GameDetail");
        }
        return detail;
    }
}
