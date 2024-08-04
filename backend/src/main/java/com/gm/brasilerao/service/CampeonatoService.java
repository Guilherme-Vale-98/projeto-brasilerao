package com.gm.brasilerao.service;

import com.gm.brasilerao.dto.CampeonatoDTO;
import com.gm.brasilerao.dto.ResponseTeamDTO;
import com.gm.brasilerao.feign.FeignClient;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService {
    private final FeignClient feignClient;

    public CampeonatoService(FeignClient feignClient) {
        this.feignClient = feignClient;
    }



    public CampeonatoDTO listStanding(String idCompetition) {
        var response= feignClient.getStandings(idCompetition);

        return  response;
    }
    public ResponseTeamDTO listTeams(String idCompetition) {
        var response= feignClient.getTeams(idCompetition);
        System.out.println(response);
        return  response;

    }


}
