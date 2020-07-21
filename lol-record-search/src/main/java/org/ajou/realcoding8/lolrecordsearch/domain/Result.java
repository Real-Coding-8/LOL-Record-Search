
package org.ajou.realcoding8.lolrecordsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Result {
    // API 응답에서 제공될 각각의 경기 정보에는 “승/패 여부", “챔피언번호", “킬, 데스, 어시스트 횟수"가 반드시 포함되어야 한다.
    @Id
    private Long resultid; //gameId*100 + participantId
    private Long gameId;
    private int participantId;
    private int teamId;
    private boolean win; // Detail -> Stats
    private int championId; // Detail -> Participants
    private int kills;
    private int deaths;
    private int assists;
}