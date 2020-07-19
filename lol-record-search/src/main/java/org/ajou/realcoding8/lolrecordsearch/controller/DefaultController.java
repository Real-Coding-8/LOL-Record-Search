package org.ajou.realcoding8.lolrecordsearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Detail;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.ajou.realcoding8.lolrecordsearch.domain.Result;
import org.ajou.realcoding8.lolrecordsearch.repository.ResultRepository;
import org.ajou.realcoding8.lolrecordsearch.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class DefaultController {

    @Autowired
    DefaultService defaultService;
    @GetMapping("/lolrecordsearch/default")
    public List<Result> getUserInfo(@RequestParam String summonerName, @RequestParam String apiKey) {
        return defaultService.getUserInfo(summonerName,apiKey);
    }
}
