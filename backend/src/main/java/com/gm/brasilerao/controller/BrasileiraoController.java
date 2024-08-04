package com.gm.brasilerao.controller;

import com.gm.brasilerao.dto.CampeonatoDTO;
import com.gm.brasilerao.dto.ResponseTeamDTO;
import com.gm.brasilerao.service.CampeonatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campeonatos")
public class BrasileiraoController {
    private final CampeonatoService campeonatoService;

    public BrasileiraoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping("/{idCompetition}/standings")
    public ResponseEntity<CampeonatoDTO> getStandings(@PathVariable String idCompetition) {
       var campeonato=campeonatoService.listStanding(idCompetition);
        return ResponseEntity.ok(campeonato);
    }

   @GetMapping("/{idCompetition}/teams")
   public ResponseEntity<ResponseTeamDTO> getTeams(@PathVariable String idCompetition) {
        var teams=campeonatoService.listTeams(idCompetition);
       return ResponseEntity.ok(teams);
   }
}