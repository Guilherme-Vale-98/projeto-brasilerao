package com.gm.brasilerao.dto;

import java.time.Instant;

public record MatchDTO(Integer matchday, ScoreDTO score, TeamDTO homeTeam, TeamDTO awayTeam, Instant utcDate) {
}
