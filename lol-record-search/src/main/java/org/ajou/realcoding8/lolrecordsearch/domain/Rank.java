package org.ajou.realcoding8.lolrecordsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Rank {
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    @Id
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;
}
