package com.gm.brasilerao.feign.dto;

import com.gm.brasilerao.dto.TeamDTO;

import java.util.List;

public record ResponseTeamDTO(List<TeamDTO>teams) {
}
