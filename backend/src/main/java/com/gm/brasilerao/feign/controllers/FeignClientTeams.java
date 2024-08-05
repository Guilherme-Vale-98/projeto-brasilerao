package com.gm.brasilerao.feign.controllers;

import com.gm.brasilerao.feign.config.FeignClientConfig;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(
        name = "apiClientTeams",
        url = "https://api.football-data.org/v4/teams",
        configuration = FeignClientConfig.class
)
public interface FeignClientTeams {
    @GetMapping("/{idTeam}/matches?status=FINISHED&competitions={idCompetition}&limit={limit}")
    ResponseMatchDTO getLastMatchesForTeam(@PathVariable("idTeam") Integer idTeam, @PathVariable("idCompetition") String idCompetition, @PathVariable("limit") Integer limit);

}