package org.ajou.realcoding8.lolrecordsearch.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Info {
    private String Id;
    private String accountId;
    private String puuid;
    @Id
    private String realname;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;
}
