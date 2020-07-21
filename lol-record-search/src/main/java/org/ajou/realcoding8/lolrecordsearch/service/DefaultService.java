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

    // step 2
    @Autowired
    private UserInfoSearchController userInfoSearchController;

    // step 3
    @Autowired
    private RankController rankController;
    // step 4
    @Autowired
    private RecordSearchController recordSearchController;

    // step 5
    @Autowired
    private GameDetailController gameDetailController;

    // step 6
    @Autowired
    private ResultRepository resultRepository;

    private int findSummonerParticipantId;
    private boolean findWin;
    private int championId;
    private int kills;
    private int deaths;
    private int assists;
    private int teamId;


    public List<Result> getUserInfo( String summonerName, String apiKey) {
        List<Result> finalResultList = new ArrayList<>(5);

        // step 2
        Info info = userInfoSearchController.getUserInfo(summonerName, apiKey);

        // step 3
        Rank rank = rankController.getRank(info.getId(), apiKey);

        // step 4
        List<Long> gameIds = recordSearchController.getGameIds(info.getAccountId(), apiKey);


        /*
            step 6
            Q1) 현재 게임에서, Step1에서 사용되었던 소환사 이름에 해당하는 participantId 는 무엇인가?
            Q2) Q1 에서 획득한 participantId의 팀은 무엇인가? (tip: 100번팀이거나, 200번 팀입니다)
            Q3) participantId 에 해당하는 유저는 게임을 승리하였는가?
            Q4) participantId 에 해당하는 유저는 어떤 챔피언을 사용하였는가?
            Q5) participantId 에 해당하는 유저의 kills, deaths, assists 점수는 어떠한가?
         */

        for(int i = 0; i < 5; i++) { // 최근 5경기를 받아 와야 한다.
            long gameId = gameIds.get(i);

            // step 5
            Detail detail = gameDetailController.getGameDetails(gameId, apiKey);

            // result domain 안에 matchId가 있는지 check
            Result result = resultRepository.findResult(gameId);

            if(result == null) {
                result = new Result();
                result.setGameId(detail.getGameId());
                for(int k = 0; k < 10; k++) {

                    // 10명의 참가자 중 입력한 소환사의 이름과 같은 participantId를 찾는다.
                    if(detail.getParticipantIdentities().get(k).getPlayer().getSummonerName().equals(rank.getSummonerName())) { // step 3가 필요
                        findSummonerParticipantId = detail.getParticipantIdentities().get(k).getParticipantId();

                        // Q1
                        //log.info("{}'s ParticipantId : {}", rank.getSummonerName(), findSummonerParticipantId);
                        result.setParticipantId(findSummonerParticipantId);
                    }

                }

                for(int j = 0; j < 10; j++) {

                    if(detail.getParticipants().get(j).getParticipantId() == findSummonerParticipantId) {
                        // Q2
                        teamId = detail.getParticipants().get(j).getTeamId();
                        //log.info("{}'s team : {}", rank.getSummonerName(), teamId);
                        result.setTeamId(teamId);

                        // Q3
                        findWin = detail.getParticipants().get(j).getStats().isWin();
                        //log.info("{}'s team is {}", rank.getSummonerName(), findWin);
                        result.setWin(findWin);

                        // Q4
                        championId = detail.getParticipants().get(j).getChampionId();
                        //log.info("{}'s championId : {}", rank.getSummonerName(), championId);
                        result.setChampionId(championId);

                        // Q5
                        kills = detail.getParticipants().get(j).getStats().getKills();
                        deaths = detail.getParticipants().get(j).getStats().getDeaths();
                        assists = detail.getParticipants().get(j).getStats().getAssists();

                        //log.info("{}'s kills : {}", rank.getSummonerName(), kills);
                        //log.info("{}'s deaths : {}", rank.getSummonerName(), deaths);
                        //log.info("{}'s assists : {}", rank.getSummonerName(), assists);

                        result.setKills(kills);
                        result.setDeaths(deaths);
                        result.setAssists(assists);
                    }
                }

                finalResultList.add(result);
                resultRepository.saveResult(result);
                log.info("({}th)save final result in DB (gameId : {})", i+1, detail.getGameId());
            }

            else {
                log.info("({}th)find in DB(Result)", i+1);
                finalResultList.add(i, result);
            }

        }
        return finalResultList;
    }
}
