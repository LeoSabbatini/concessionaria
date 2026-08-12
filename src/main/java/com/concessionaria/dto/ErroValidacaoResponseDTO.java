package com.concessionaria.dto;

import java.util.List;

public record ErroValidacaoResponseDTO(
        int status,
        List<CampoErroDTO> erros
) {
}