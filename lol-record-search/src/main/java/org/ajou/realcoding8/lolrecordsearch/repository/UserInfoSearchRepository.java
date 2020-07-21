package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserInfoSearchRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public Info findUserInfo(String summonerName) {
        Query query = Query.query(
                Criteria.where("_id").is(summonerName)
        );
        Info info = mongoTemplate.findOne(query, Info.class);
        return info;
    }

    public void saveUserInfo(Info info) {
        mongoTemplate.save(info);
    }
}
