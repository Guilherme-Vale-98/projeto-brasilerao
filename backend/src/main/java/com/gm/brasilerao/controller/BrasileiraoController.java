package com.gm.brasilerao.controller;

import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.service.CampeonatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/championship")
public class BrasileiraoController {
    private final CampeonatoService campeonatoService;

    public BrasileiraoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping("/{idCompetition}/standings")
    public ResponseEntity<ResponseCampeonatoDTO> getStandings(@PathVariable String idCompetition) {
        var championship=campeonatoService.ListStanding(idCompetition);
        return ResponseEntity.ok(championship);
    }

    @GetMapping("/{idCompetition}/teams")
    public ResponseEntity<ResponseTeamDTO> getTeams(@PathVariable String idCompetition) {
        var teams=campeonatoService.ListTeams(idCompetition);
        return ResponseEntity.ok(teams);
    }
    @GetMapping("/{idCompetition}/teams/{idTeam}/matches/last{limit}")
    public ResponseEntity<ResponseMatchDTO> getLastMatchesByTeam(@PathVariable String idCompetition,@PathVariable Integer idTeam,@PathVariable Integer limit) {
        var matches=campeonatoService.ListLastFiveMatches(idTeam,idCompetition,limit);
        return ResponseEntity.ok(matches);
    }
}