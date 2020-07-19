package org.ajou.realcoding8.lolrecordsearch.domain;

import lombok.Data;

import java.util.List;

@Data
public class Match {
    private String EncryptedAccountId;
    private List<Match_> matches;
    /*
    private long startIndex;
    private long endIndex;
    private long totalGames;


     */
    @Data
    public static class Match_{
        //private String platformId;
        private long gameId;
        /*
        private long champion;
        private long queue;
        private long season;
        private long timestamp;
        private String role;
        private String lane;

         */
    }
}