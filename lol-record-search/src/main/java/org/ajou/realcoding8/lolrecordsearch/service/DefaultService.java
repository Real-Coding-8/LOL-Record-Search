package org.ajou.realcoding8.lolrecordsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.controller.GameDetailController;
import org.ajou.realcoding8.lolrecordsearch.controller.RankController;
import org.ajou.realcoding8.lolrecordsearch.controller.RecordSearchController;
import org.ajou.realcoding8.lolrecordsearch.controller.UserInfoSearchController;
import org.ajou.realcoding8.lolrecordsearch.domain.Detail;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.ajou.realcoding8.lolrecordsearch.domain.Rank;
import org.ajou.realcoding8.lolrecordsearch.domain.Result;
import org.ajou.realcoding8.lolrecordsearch.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class DefaultService {

    //step2
    @Autowired
    UserInfoSearchService userInfoSearchService;
    //step3
    @Autowired
    RankService rankService;
    //step4
    @Autowired
    GameIdSearchService gameIdSearchService;
    //step5
    @Autowired
    GameDetailService gameDetailService;

    /*
    step 6
    Q1) 현재 게임에서, Step1에서 사용되었던 소환사 이름에 해당하는 participantId 는 무엇인가?
    Q2) Q1 에서 획득한 participantId의 팀은 무엇인가? (tip: 100번팀이거나, 200번 팀입니다)
    Q3) participantId 에 해당하는 유저는 게임을 승리하였는가?
    Q4) participantId 에 해당하는 유저는 어떤 챔피언을 사용하였는가?
    Q5) participantId 에 해당하는 유저의 kills, deaths, assists 점수는 어떠한가?
 */
    @Autowired
    ResultRepository resultRepository;

    public int getParticipantId(String SummonerName, long gameId, String apiKey) {
        Detail detail = gameDetailService.getGameDetails(gameId, apiKey);
        int participantId = 0;
        String accountId = userInfoSearchService.getUserInfo(SummonerName,apiKey).getAccountId();
        for (Detail.ParticipantIdentities p : detail.getParticipantIdentities()) {
            if (p.getPlayer().getAccountId().equals(accountId)) {
                participantId = p.getParticipantId();
            }
        }
        return participantId;
    }

    public Result getFinalresult_(String SummonerName, long gameId, String apiKey){
        Detail detail = gameDetailService.getGameDetails(gameId,apiKey);
        Result finalresult = new Result();
        int participantId = getParticipantId(SummonerName,gameId,apiKey);
        long resultId = detail.getGameId()*100+participantId;

        if((finalresult = resultRepository.findResult(resultId))!=null) {

            log.info("find final result in DB (resutId : {})", finalresult.getResultid());
        }
        else{
            log.info("gameId {}  par{}",detail.getGameId(),participantId);
            Detail.Participants participants = detail.getParticipants().get(participantId-1);
            finalresult = new Result();
            finalresult.setResultid(resultId);
            finalresult.setChampionId(participants.getChampionId());
            finalresult.setParticipantId(participantId);
            finalresult.setTeamId(participants.getTeamId());
            finalresult.setKills(participants.getStats().getKills());
            finalresult.setAssists(participants.getStats().getAssists());
            finalresult.setDeaths(participants.getStats().getDeaths());
            finalresult.setWin(participants.getStats().isWin());
            finalresult.setResultid(detail.getGameId()*100+participantId);

            resultRepository.saveResult(finalresult);

            log.info("save final result in DB (resutId : {})", finalresult.getResultid());
            log.info("success getFinalresults about {}",gameId);
        }
        return finalresult;
    }


    public List<Result> getUserInfo( String summonerName, String apiKey) {
        List<Result> finalResultList = new ArrayList<>(5);
        String accountId = userInfoSearchService.getUserInfo(summonerName,apiKey).getAccountId();
        List<Long> gameId = gameIdSearchService.getGameIds(accountId,apiKey);
        List<Long> gameId_5 = gameId.subList(0,5);

        for(Long i : gameId_5)
            finalResultList.add(getFinalresult_(summonerName,i,apiKey));

        return finalResultList;
    }
}
