package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.api.UserInfoApi;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.repository.UserInfoSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoSearchService {

    @Autowired
    private UserInfoApi userInfoApi;
    @Autowired
    private UserInfoSearchRepository userInfoSearchRepository;


    public Info getUserInfo(String summonerName, String apiKey) {
        Info info = new Info();
        info = userInfoSearchRepository.findUserInfo(summonerName);
        if(info != null) {
            log.info("find in DB");
            return info;
        }
        else {
            info = userInfoApi.getUserInfo(summonerName, apiKey);
            userInfoSearchRepository.saveUserInfo(info);
            return info;
        }

    }
}
