package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
