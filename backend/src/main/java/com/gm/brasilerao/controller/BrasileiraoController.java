package com.gm.brasilerao.controller;

import com.gm.brasilerao.dto.MatchDTO;
import com.gm.brasilerao.dto.TableEntryDTO;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.service.ChampionshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/championship")
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
    @GetMapping("/{idCompetition}/standingsWithMatches")
    public ResponseEntity getStandingsWithMatch(@PathVariable String idCompetition) {
        ResponseCampeonatoDTO championship= championshipService.ListStanding(idCompetition);
        ResponseMatchDTO championshipMatches = championshipService.ListStandingMatches(idCompetition);

        List<TableEntryDTO> table = championship.standings().getFirst().table();
        List<MatchDTO> matches = championshipMatches.matches();
        matches.stream().forEach(match -> updateTableWithMatchResult(match, table));

//        table.forEach(tableEntryDTO -> {
//            List<String> formerMatches = tableEntryDTO.formerMatches();
//            if (formerMatches.size() > 5) {
//                List<String> lastFiveMatches = new ArrayList<>(formerMatches.subList(formerMatches.size() - 5, formerMatches.size()));
//                tableEntryDTO.formerMatches().clear();
//                tableEntryDTO.formerMatches().addAll(lastFiveMatches);
//            }
//        });

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