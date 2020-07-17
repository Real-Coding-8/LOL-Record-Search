package org.ajou.realcoding8.lolrecordsearch.controller;

import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.ajou.realcoding8.lolrecordsearch.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/rank-service/rank")
    public Rank getRank(@RequestParam String encryptedSummonerId) throws IOException {
        return rankService.getSoloRank(encryptedSummonerId);
    }
}
