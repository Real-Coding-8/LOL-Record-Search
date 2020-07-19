package org.ajou.realcoding8.lolrecordsearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.service.GameIdSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RecordSearchController {

    @Autowired
    private GameIdSearchService gameIdSearchService;



    @GetMapping("/lolrecordsearch/userinfo")
    public Info getUserInfo(@RequestParam String summonerName, @RequestParam String apiKey){
        return gameIdSearchService.getUserInfo(summonerName, apiKey);
    }

}
