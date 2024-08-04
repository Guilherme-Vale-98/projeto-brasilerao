package com.gm.brasilerao.service;

import com.gm.brasilerao.feign.controllers.FeignClientTeams;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.feign.controllers.FeignClientCompetition;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService {
    private final FeignClientTeams feignClientTeams;
    private final FeignClientCompetition feignClientCompetition;

    public CampeonatoService(FeignClientTeams feignClientTeams, FeignClientCompetition feignClientCompetition) {
        this.feignClientTeams = feignClientTeams;
        this.feignClientCompetition = feignClientCompetition;
    }



    public ResponseCampeonatoDTO listStanding(String idCompetition) {
        var response= feignClientCompetition.getStandings(idCompetition);

        return  response;
    }
    public ResponseTeamDTO listTeams(String idCompetition) {
        var response= feignClientCompetition.getTeams(idCompetition);
        System.out.println(response);
        return  response;

    }


    public ResponseMatchDTO ListLastFiveMatches(Integer idTeam,String idCompetition,Integer limit) {
        var response=feignClientTeams.getTeamsLast5MatchesForTeam(idTeam,idCompetition,limit) ;
        System.out.println(response);
        return  response;
    }
}
