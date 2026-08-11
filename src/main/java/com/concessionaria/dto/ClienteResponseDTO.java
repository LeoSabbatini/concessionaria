package com.concessionaria.dto;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        String email,
        String telefone
) {
}