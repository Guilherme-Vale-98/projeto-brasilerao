package com.gm.brasilerao.dto;

public record TableEntryDTO(int position,TeamDTO team,int playedGames,int won,
                            int draw,int lost,int points,int goalsFor,int goalsAgainst,int goalsDifference) {
}
