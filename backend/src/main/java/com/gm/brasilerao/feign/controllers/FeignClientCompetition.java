package com.gm.brasilerao.feign.controllers;

import com.gm.brasilerao.feign.config.FeignClientConfig;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.cloud.openfeign.FeignClient(
        name = "apiClientCompetition",
        url = "https://api.football-data.org/v4/competitions",
        configuration = FeignClientConfig.class
)
public interface FeignClientCompetition {
    @RequestMapping("")
    @GetMapping("/{idCompeticao}/standings")
    ResponseCampeonatoDTO getStandings(@PathVariable("idCompeticao") String idCompeticao);

    @GetMapping("/{idCompeticao}/teams")
    ResponseTeamDTO getTeams(@PathVariable("idCompeticao") String idCompeticao);






}