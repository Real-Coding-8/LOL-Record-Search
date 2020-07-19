package org.ajou.realcoding8.lolrecordsearch.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding8.lolrecordsearch.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class UserInfoApi {
    @Autowired  //config에서 주입을 해줬기 때문에 autowired가능.
    RestTemplate restTemplate = new RestTemplate();

    private static final String USERINFO_REQUEST_URL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={apiKey}";

    public Info getUserInfo(String summonerName, String apiKey){
        Info info = restTemplate.getForObject(USERINFO_REQUEST_URL, Info.class, summonerName, apiKey);
        log.info("Asdasd");
        return info;
    }

}
