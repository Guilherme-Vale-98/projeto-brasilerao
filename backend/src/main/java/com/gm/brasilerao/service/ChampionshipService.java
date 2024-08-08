package com.gm.brasilerao.service;

import com.gm.brasilerao.dto.MatchDTO;
import com.gm.brasilerao.dto.TableEntryDTO;
import com.gm.brasilerao.feign.controllers.FeignClientTeams;
import com.gm.brasilerao.feign.dto.ResponseCampeonatoDTO;
import com.gm.brasilerao.feign.dto.ResponseMatchDTO;
import com.gm.brasilerao.feign.dto.ResponseTeamDTO;
import com.gm.brasilerao.feign.controllers.FeignClientCompetition;
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



    public ResponseCampeonatoDTO ListStanding(String idCompetition) {
        var campeonatoDTO = feignClientCompetition.getStanding(idCompetition);
        ResponseMatchDTO championshipMatches = feignClientCompetition.getAllFinishedMatches(idCompetition);

        List<TableEntryDTO> table = campeonatoDTO.standings().getFirst().table();

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

        return  campeonatoDTO;
    }

    public ResponseMatchDTO ListStandingMatches(String idCompetition) {
        ResponseMatchDTO standingMatches = feignClientCompetition.getAllFinishedMatches(idCompetition);
        return  standingMatches;
    }

    public ResponseTeamDTO ListTeams(String idCompetition) {
        var teams= feignClientCompetition.getTeams(idCompetition);
        return teams;

    }


    public ResponseMatchDTO ListLastMatchesByTeam(Integer idTeam,String idCompetition,String status,Integer limit) {
        var matches=feignClientTeams.getLastMatchesForTeam(idTeam,idCompetition,status,limit) ;
        return matches;
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