package org.ajou.realcoding8.lolrecordsearch.domain;


import lombok.Data;

@Data
public class Info {
    private String Id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;
}
