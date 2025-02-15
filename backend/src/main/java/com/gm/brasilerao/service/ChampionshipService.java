package com.gm.brasilerao.service;

import com.gm.brasilerao.dto.MatchDTO;
import com.gm.brasilerao.dto.TableEntryDTO;
import com.gm.brasilerao.feign.controllers.FeignClientTeams;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.feign.controllers.FeignClientCompetition;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChampionshipService {
    private final FeignClientTeams feignClientTeams;
    private final FeignClientCompetition feignClientCompetition;

    public ChampionshipService(FeignClientTeams feignClientTeams, FeignClientCompetition feignClientCompetition) {
        this.feignClientTeams = feignClientTeams;
        this.feignClientCompetition = feignClientCompetition;
    }



    public Resultado<ResponseCampeonatoDTO> ListStanding(String idCompetition) {
        try {
            var campeonatoDTO = feignClientCompetition.getStanding(idCompetition).getBody();
            var championshipMatches = feignClientCompetition.getAllFinishedMatches(idCompetition).getBody();

            List<TableEntryDTO> table = campeonatoDTO.standings().getFirst().table();

            List<MatchDTO> matches = championshipMatches.matches();

            matches.stream().forEach(match -> updateTableWithMatchResult(match, table));

            return new Resultado<>(campeonatoDTO);

        }catch (FeignException e){
            System.out.println("erro na requisição: " + e.getMessage() + e.status());

            if(e.status()==429){

                return new Resultado<>("You reached your request limit");
            }else{
                return  new Resultado<>("Erro na requisição");
            }


        }catch (Exception e){
            return  new Resultado<>("Erro na requisição");
        }

    }

    public Resultado<ResponseMatchDTO> ListStandingMatches(String idCompetition) {
        try {
            ResponseMatchDTO standingMatches = feignClientCompetition.getAllFinishedMatches(idCompetition).getBody();

            return  new Resultado<>(standingMatches);

        }catch (FeignException e){

            if(e.status()==429){
                return new Resultado<>("You reached your request limit");
            }


            return new Resultado<>("Erro na requisição");
        }catch (Exception e){
            return  new Resultado<>("erro na requisição");
        }

    }

    public Resultado<ResponseTeamDTO> ListTeams(String idCompetition) {
        try {
            var teams= feignClientCompetition.getTeams(idCompetition).getBody();
            return new Resultado<>(teams);
        }catch (FeignException e){
            if(e.status()==429){
                return new Resultado<>("You reached your request limit");
            }

            return new Resultado<>("Erro na requisição:"+e.getMessage());

        }catch (Exception e){
            return new Resultado<>("Erro na requisição:"+e.getMessage());
        }


    }


    public Resultado<ResponseMatchDTO> ListLastMatchesByTeam(Integer idTeam,String idCompetition,String status,Integer limit) {
       try {
           ResponseMatchDTO matches= feignClientTeams.getLastMatchesForTeam(idTeam,idCompetition,status,limit).getBody();
           return new Resultado<>(matches);

       }catch (FeignException e){
           if(e.status()==429){
               return new Resultado<>("You reached your request limit");
           }
            System.out.println("Erro na requisição:"+e.getMessage());
           return new Resultado<>("Erro na requisição:"+e.getMessage());

       }catch (Exception e){
           System.out.println("Erro na requisição:"+e.getMessage());
           return  new Resultado<>("Erro na requisição:"+e.getMessage());
       }


    }

    private static void updateTableWithMatchResult(MatchDTO match, List<TableEntryDTO> table) {
        String winner = match.score().winner();
        switch (winner) {
            case "AWAY_TEAM":
                addMatchResultsToTable(
                        match.awayTeam().shortName(),
                        match.homeTeam().shortName(),
                        table
                );
                break;
            case "HOME_TEAM":
                addMatchResultsToTable(
                        match.homeTeam().shortName(),
                        match.awayTeam().shortName(),
                        table
                );
                break;
            case "DRAW":
                addDrawResultsToTable(
                        match.homeTeam().shortName(),
                        match.awayTeam().shortName(),
                        table
                );
                break;
            default:
                System.out.println("Unknown result");
                break;
        }
    }

    private static void addDrawResultsToTable(String team1, String team2, List<TableEntryDTO> table) {
        Optional<TableEntryDTO> team1Optional = table.stream().filter(entry -> entry.team().shortName().equals(team1)).findFirst();
        if (team1Optional.isPresent()){
            team1Optional.get().formerMatches().add("D");
        }
        Optional<TableEntryDTO> team2Optional = table.stream().filter(entry -> entry.team().shortName().equals(team2)).findFirst();
        if (team2Optional.isPresent()){
            team2Optional.get().formerMatches().add("D");
        }
    }

    private static void addMatchResultsToTable(String winner, String loser, List<TableEntryDTO> table) {
        //find winner and loser and update
        Optional<TableEntryDTO> winnerOptional = table.stream().filter(entry -> entry.team().shortName().equals(winner)).findFirst();
        if (winnerOptional.isPresent()){
            winnerOptional.get().formerMatches().add("W");
        }
        Optional<TableEntryDTO> loserOptional = table.stream().filter(entry -> entry.team().shortName().equals(loser)).findFirst();
        if (loserOptional.isPresent()){
            loserOptional.get().formerMatches().add("L");
        }
    }




}