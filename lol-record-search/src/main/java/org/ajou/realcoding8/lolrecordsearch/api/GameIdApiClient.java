package org.ajou.realcoding8.lolrecordsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class GameIdApiClient {
    @Autowired  //config에서 주입을 해줬기 때문에 autowired가능.
    RestTemplate restTemplate = new RestTemplate();
    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{encryptedAccountId}?api_key=RGAPI-de5944c0-a4b3-4ee6-a273-a7188807d860";


    public Match getMatches(String encryptedAccountId){
         Match gameId = restTemplate.getForObject(GAMEID_REQUEST_URI, Match.class, encryptedAccountId);
         log.info("Success finding Matches about AccountId : {}",encryptedAccountId);
         gameId.setEncryptedAccountId(encryptedAccountId);
        return gameId;
    }
}
