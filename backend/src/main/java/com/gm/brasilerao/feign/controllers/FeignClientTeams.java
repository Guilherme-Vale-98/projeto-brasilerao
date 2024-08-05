package com.gm.brasilerao.feign.controllers;

import com.gm.brasilerao.feign.config.FeignClientConfig;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(
        name = "apiClientTeams",
        url = "https://api.football-data.org/v4/teams",
        configuration = FeignClientConfig.class
)
public interface FeignClientTeams {
    public final String STATUS="FINISHED";
    @GetMapping("/{idTeam}/matches")
    ResponseMatchDTO getLastMatchesForTeam(@PathVariable("idTeam") Integer idTeam,
                                           @RequestParam("idCompetition") String idCompetition,
                                           @RequestParam("status") String status,
                                           @RequestParam("limit") Integer limit);

}


