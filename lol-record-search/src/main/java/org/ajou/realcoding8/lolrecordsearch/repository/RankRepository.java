package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RankRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveRank(Rank rank){
        Rank savedRank = mongoTemplate.save(rank);
        log.info("Saved Rank : {}", savedRank);
    }
}
