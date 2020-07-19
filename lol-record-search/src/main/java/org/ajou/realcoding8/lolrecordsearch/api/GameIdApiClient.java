package org.ajou.realcoding8.lolrecordsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Details;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class GameIdApiClient {
    @Autowired  //config에서 주입을 해줬기 때문에 autowired가능.
    RestTemplate restTemplate = new RestTemplate();
    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{encryptedAccountId}?api_key={apiKey}";


    public Match getMatches(String encryptedAccountId, String apiKey){
         Match gameId = restTemplate.getForObject(GAMEID_REQUEST_URI, Match.class, encryptedAccountId, apiKey);
         log.info("Success finding Matches about AccountId : {}",encryptedAccountId);


        return gameId;
    }

    private static final String MATCH_REQUEST_URL = "https://kr.api.riotgames.com/lol/match/v4/matches/{matchId}?api_key={apiKey}";

    public Details getGameDetails(String matchId, String apiKey) {
        Details details = restTemplate.getForObject(MATCH_REQUEST_URL, Details.class, matchId, apiKey);
        log.info("Success finding Game Details about matchId : {}", matchId);
        return details;
    }

    private static final String USERINFO_REQUEST_URL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={apiKey}";

    public Info getUserInfo(String summonerName, String apiKey){
        Info info = restTemplate.getForObject(USERINFO_REQUEST_URL, Info.class, summonerName, apiKey);
        log.info("Asdasd");
        return info;
    }

}
