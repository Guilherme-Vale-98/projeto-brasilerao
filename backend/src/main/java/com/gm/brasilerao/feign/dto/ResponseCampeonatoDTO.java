package com.gm.brasilerao.feign.dto;

import com.gm.brasilerao.dto.*;

import java.util.List;

public record ResponseCampeonatoDTO(FiltersDTO filters, AreaDTO area, CompetitionDTO competition, SeasonDTO season,
                                    List<StandingsDTO> standings) {
}
