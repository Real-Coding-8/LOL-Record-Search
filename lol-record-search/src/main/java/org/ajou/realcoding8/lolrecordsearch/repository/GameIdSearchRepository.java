package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class GameIdSearchRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveMatches(Match match){
        mongoTemplate.save(match);
    }

    public Match findCurrentWeather(String encryptedAccountId){
        Query query = Query.query(
                Criteria.where("EncryptedAccountId").is(encryptedAccountId)
        );
        Match match = mongoTemplate.findOne(query, Match.class);
        return match;
    }
}
