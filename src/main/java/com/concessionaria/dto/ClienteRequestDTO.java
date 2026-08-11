package com.concessionaria.dto;

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        String telefone
) {
}