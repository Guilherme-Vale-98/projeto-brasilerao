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



    public ResponseCampeonatoDTO ListStanding(String idCompetition) {
        var Standing= feignClientCompetition.getStanding(idCompetition);
        return  Standing;
    }
    public ResponseTeamDTO ListTeams(String idCompetition) {
        var teams= feignClientCompetition.getTeams(idCompetition);
        return teams;

    }


    public ResponseMatchDTO ListLastFiveMatches(Integer idTeam,String idCompetition,Integer limit) {
        var matches=feignClientTeams.getLastMatchesForTeam(idTeam,idCompetition,limit) ;
        return matches;
    }
}