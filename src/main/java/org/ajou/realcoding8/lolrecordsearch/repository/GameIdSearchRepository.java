package org.ajou.realcoding8.lolrecordsearch.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Details;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
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

    public void saveGameDetails(Details details) {
        mongoTemplate.save(details);
    }

    public Details findGameDetails(String matchId) {
        Query query = Query.query(
                Criteria.where("matchId").is(matchId)
        );
        Details details = mongoTemplate.findOne(query, Details.class);
        return details;
    }

    public Info findUserInfo(String summonerName) {
        Query query = Query.query(
                Criteria.where("summonerName").is(summonerName)
        );
        Info info = mongoTemplate.findOne(query, Info.class);
        return info;
    }

    public void saveUserInfo(Info info) {
        mongoTemplate.save(info);
    }
}
