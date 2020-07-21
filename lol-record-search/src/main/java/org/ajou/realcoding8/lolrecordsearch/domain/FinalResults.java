package org.ajou.realcoding8.lolrecordsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class FinalResults {
    private String tier;
    private String rank;
    @Id
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private List<Result> results;

}
