package com.gm.brasilerao.feign.dto;

import com.gm.brasilerao.dto.MatchDTO;

import java.util.List;

public record ResponseMatchDTO(List<MatchDTO> matches) {
}
