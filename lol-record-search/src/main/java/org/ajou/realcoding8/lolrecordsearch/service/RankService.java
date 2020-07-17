package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.api.RiotApiClient;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.ajou.realcoding8.lolrecordsearch.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RankService {

    @Autowired
    private RiotApiClient riotApiClient;
    @Autowired
    private RankRepository rankRepository;

    public Rank getSoloRank(String encryptedSummonerId) {
        Rank[] ranks = riotApiClient.getAllRank(encryptedSummonerId);
        for(Rank rank : ranks){
            if (rank.getQueueType().equals("RANKED_SOLO_5x5")){
                log.info("solo rank tier: {}",rank.getTier());
                //rankRepository.saveRank(rank);
                return rank;
            }
        }
        return null;
    }
}
