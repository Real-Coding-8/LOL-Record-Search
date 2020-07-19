package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class GameDetailRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveGameDetail(Detail detail) {
        mongoTemplate.save(detail);
    }

    public Detail findGameDetail(String matchId) {
        Query query = Query.query(
                Criteria.where("matchId").is(matchId)
        );
        Detail detail = mongoTemplate.findOne(query, Detail.class);
        return detail;
    }

}
