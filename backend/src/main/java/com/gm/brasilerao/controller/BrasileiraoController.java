package com.gm.brasilerao.controller;

import com.gm.brasilerao.dto.MatchDTO;
import com.gm.brasilerao.dto.TableEntryDTO;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.service.ChampionshipService;
import com.gm.brasilerao.service.Resultado;
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
    public ResponseEntity<?> getStandings(@PathVariable String idCompetition) {
        var resultado=championshipService.ListStanding(idCompetition);

        if(resultado.isSucesso()){
            ResponseCampeonatoDTO dados=resultado.getSucesso();
            return ResponseEntity.ok(dados);
        }else{

            String erro=resultado.getErro();

            if(erro.contains("You reached your request limit")){
                Resultado<String>mensagem_erro=new Resultado<>(erro);
                return  ResponseEntity.status(429).body(mensagem_erro);

            }



            Resultado<String>mensagem_erro=new Resultado<>(erro);
            return ResponseEntity.badRequest().build();
        }


    }



    @GetMapping("/{idCompetition}/standings/matches")
    public ResponseEntity<?> getStandingsMatches(@PathVariable String idCompetition) {
        var response = championshipService.ListStandingMatches(idCompetition);
        if (response.isSucesso()) {
            ResponseMatchDTO dados = response.getSucesso();
            return ResponseEntity.ok(dados);
        }else {
            String erro=response.getErro();
            if(erro.contains("You reached your request limit")){
                Resultado<String>mensagem_erro=new Resultado<>(erro);
                return ResponseEntity.status(429).body(mensagem_erro);
            }
            Resultado<String>mensagem_erro=new Resultado<>(erro);
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping("/{idCompetition}/teams")
    public ResponseEntity<?> getTeams(@PathVariable String idCompetition) {
        var response= championshipService.ListTeams(idCompetition);
        if (response.isSucesso()) {
            return ResponseEntity.ok(response);
        }else{
            String erro=response.getErro();
            if(erro.contains("You reached your request limit")){
                Resultado<String>mensagem_erro=new Resultado<>(erro);
                return ResponseEntity.status(429).body(mensagem_erro);
            }
            return ResponseEntity.status(400).build();
        }

    }
    @GetMapping("/{idCompetition}/teams/{idTeam}/matches")
    public ResponseEntity<?> getLastMatchesByTeam(@PathVariable String idCompetition,@PathVariable Integer idTeam,@RequestParam(defaultValue = "5") Integer limit) {
        final String STATUS="FINISHED";
        var response=championshipService.ListLastMatchesByTeam(idTeam,idCompetition,STATUS,limit);
        if (response.isSucesso()) {
            return ResponseEntity.ok(response);
        }else{
            String erro=response.getErro();
            if(erro.contains("You reached your request limit")){
                Resultado<String>mensagem_erro=new Resultado<>(erro);
                return ResponseEntity.status(429).body(mensagem_erro);
            }
            Resultado<String>mensagem_erro=new Resultado<>(erro);
            return ResponseEntity.status(400).build();
        }


    }


}