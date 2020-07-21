package org.ajou.realcoding8.lolrecordsearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.service.UserInfoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserInfoSearchController {

    @Autowired
    private UserInfoSearchService userInfoSearchService;



    //@GetMapping("/lolrecordsearch/userinfo")
    public Info getUserInfo(@RequestParam String summonerName, @RequestParam String apiKey){
        return userInfoSearchService.getUserInfo(summonerName, apiKey);
    }

}
