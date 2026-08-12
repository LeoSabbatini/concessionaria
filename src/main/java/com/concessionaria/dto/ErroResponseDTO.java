package com.concessionaria.dto;

public record ErroResponseDTO(
        int status,
        String mensagem
) {
}