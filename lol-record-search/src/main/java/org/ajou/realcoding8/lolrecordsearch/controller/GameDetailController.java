package org.ajou.realcoding8.lolrecordsearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Detail;
import org.ajou.realcoding8.lolrecordsearch.service.GameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GameDetailController {

    @Autowired
    private GameDetailService gameDetailService;

    //@GetMapping("lolrecordsearch/gamedetails")
    public Detail getGameDetails(@RequestParam long gameId, @RequestParam String apiKey) {
        return gameDetailService.getGameDetails(gameId, apiKey);
    }
}
