package org.ajou.realcoding8.lolrecordsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Detail {
    @Id
    private Long gameId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;
    private int queueId;
    private int mapId;
    private int seasonId;
    private String gameVersion;
    private String gameMode;
    private String gameType;
    private List<Teams> teams;
    private List<Participants> participants;
    private List<ParticipantIdentities> participantIdentities;

    @Data
    public static class ParticipantIdentities
    {
        private int participantId;
        private Player player;
    }

    @Data
    public static class Player
    {
        private String platformId;
        private String accountId;
        private String summonerName;
        private String summonerId;
        private String currentPlatformId;
        private String currentAccountId;
        private String matchHistoryUri;
        private int profileIcon;
    }

    @Data
    public static class Participants
    {
        private int participantId;
        private int teamId;
        private int championId;
        private int spell1Id;
        private int spell2Id;
        private Stats stats;
    }

    @Data
    public static class Stats
    {
        private int participantId;
        private boolean win;
        private int kills;
        private int deaths;
        private int assists;
    }

    @Data
    public static class Teams
    {
        private int teamId;
        private String win;
        private boolean firstBlood;
        private boolean firstTower;
        private boolean firstInhibitor;
        private boolean firstBaron;
        private boolean firstDragon;
        private boolean firstRiftHerald;
        private int towerKills;
        private int inhibitorKills;
        private int baronKills;
        private int dragonKills;
        private int vilemawKills;
        private int riftHeraldKills;
        private int dominionVictoryScore;
        private List<Bans> bans;
    }
    @Data
    public static class Bans
    {
        private int championId;
        private int pickTurn;
    }
}
