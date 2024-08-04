package com.gm.brasilerao.dto;

import java.util.List;

public record CampeonatoDTO(FiltersDTO filters, AreaDTO area, CompetitionDTO competition,SeasonDTO season,
                            List<StandingsDTO> standings) {
}
