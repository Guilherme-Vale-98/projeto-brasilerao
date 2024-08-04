package com.gm.brasilerao.feign;

import com.gm.brasilerao.dto.CampeonatoDTO;
import com.gm.brasilerao.dto.ResponseTeamDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(
        name = "apiClient",
        url = "https://api.football-data.org/v4/competitions",
        configuration = FeignClientConfig.class
)
public interface FeignClient {
    @GetMapping("/{idCompeticao}/standings")
    CampeonatoDTO getStandings(@PathVariable("idCompeticao") String idCompeticao);

    @GetMapping("/{idCompeticao}/teams")
    ResponseTeamDTO getTeams(@PathVariable("idCompeticao") String idCompeticao);




}