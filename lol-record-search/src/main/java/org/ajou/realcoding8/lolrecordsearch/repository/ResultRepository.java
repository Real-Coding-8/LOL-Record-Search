package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.FinalResults;
import org.ajou.realcoding8.lolrecordsearch.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ResultRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveResult(Result finalResult) {

        mongoTemplate.save(finalResult);
    }

    public void saveFinalResults(FinalResults finalResults){
        mongoTemplate.save(finalResults);
    }

    public Result findResult(long id) {
        Query query = Query.query(
                Criteria.where("_id").is(id)
        );
        Result result = mongoTemplate.findOne(query, Result.class);
        return result;
    }
}
