package com.gm.brasilerao.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record TableEntryDTO(int position, TeamDTO team, int playedGames, int won,
                            int draw, int lost, int points, int goalsFor, int goalsAgainst, int goalsDifference, List<String> formerMatches) {

    public TableEntryDTO {
        formerMatches = Objects.requireNonNullElse(formerMatches, new ArrayList<>());
    }
}
