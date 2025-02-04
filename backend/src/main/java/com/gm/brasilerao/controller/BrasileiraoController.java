package com.gm.brasilerao.controller;

import com.gm.brasilerao.dto.MatchDTO;
import com.gm.brasilerao.dto.TableEntryDTO;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.service.ChampionshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/championship")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class BrasileiraoController {
    private final ChampionshipService championshipService;

    public BrasileiraoController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping("/{idCompetition}/standings")
    public ResponseEntity<ResponseCampeonatoDTO> getStandings(@PathVariable String idCompetition) {
        var championship= championshipService.ListStanding(idCompetition);
        return ResponseEntity.ok(championship);
    }

    @GetMapping("/{idCompetition}/standings/matches")
    public ResponseEntity<ResponseMatchDTO> getStandingsMatches(@PathVariable String idCompetition) {
        var championshipMatches = championshipService.ListStandingMatches(idCompetition);
        return ResponseEntity.ok(championshipMatches);
    }

    @GetMapping("/{idCompetition}/teams")
    public ResponseEntity<ResponseTeamDTO> getTeams(@PathVariable String idCompetition) {
        var teams= championshipService.ListTeams(idCompetition);
        return ResponseEntity.ok(teams);
    }
    @GetMapping("/{idCompetition}/teams/{idTeam}/matches")
    public ResponseEntity<ResponseMatchDTO> getLastMatchesByTeam(@PathVariable String idCompetition,@PathVariable Integer idTeam,@RequestParam(defaultValue = "5") Integer limit) {
        final String STATUS="FINISHED";
        var matches= championshipService.ListLastMatchesByTeam(idTeam,idCompetition,STATUS,limit);
        return ResponseEntity.ok(matches);
    }


}