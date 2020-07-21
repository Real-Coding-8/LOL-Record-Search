package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.api.RankApiClient;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.ajou.realcoding8.lolrecordsearch.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RankService {

    @Autowired
    private RankApiClient rankApiClient;
    @Autowired
    private RankRepository rankRepository;

    public Rank getSoloRank(String encryptedSummonerId, String apiKey) {
        Rank finalrank = new Rank();
        if((finalrank = rankRepository.findRank(encryptedSummonerId))!=null){
            log.info("find rank in DB");
        }
        else {
        Rank[] ranks = rankApiClient.getAllRank(encryptedSummonerId, apiKey);
        for(Rank rank : ranks){
            if (rank.getQueueType().equals("RANKED_SOLO_5x5")){
                log.info("solo rank tier: {}",rank.getTier());
                rankRepository.saveRank(rank);
                finalrank=rank;
                }
            }
        }
        return finalrank;
    }
}
