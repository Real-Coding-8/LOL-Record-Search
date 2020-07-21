package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.ajou.realcoding8.lolrecordsearch.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public Rank findRank(String summonerId){
        Query query = Query.query(
                Criteria.where("_id").is(summonerId)
        );
        Rank rank = mongoTemplate.findOne(query, Rank.class);
        return rank;
    }
}
