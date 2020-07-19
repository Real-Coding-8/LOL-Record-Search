package org.ajou.realcoding8.lolrecordsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GameDetailApiClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MATCH_REQUEST_URL = "https://kr.api.riotgames.com/lol/match/v4/matches/{matchId}?api_key={apiKey}";

    public Detail getGameDetail(String matchId, String apiKey) {
        Detail detail = restTemplate.getForObject(MATCH_REQUEST_URL, Detail.class, matchId, apiKey);
        log.info("Success finding Game Details about matchId : {}", matchId);
        return detail;
    }
}
