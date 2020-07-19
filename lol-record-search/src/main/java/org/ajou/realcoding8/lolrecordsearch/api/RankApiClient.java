package org.ajou.realcoding8.lolrecordsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RankApiClient {
    @Autowired
    private RestTemplate restTemplate;
    private static final String RANK_REQUEST_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-70981269-9b88-44de-81fb-f666e549b36f";

    public Rank[] getAllRank(String encryptedSummonerId){
        ResponseEntity<Rank[]> rankEntity = restTemplate.getForEntity(RANK_REQUEST_URI, Rank[].class, encryptedSummonerId);
        Rank[] ranks = rankEntity.getBody();
        return ranks;
    }
}
