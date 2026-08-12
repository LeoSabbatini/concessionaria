package com.concessionaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank(message = "informe o nome do cliente")
        @Size(min = 3, max = 150, message = "o nome deve ter entre 3 e 150 caracteres")
        String nome,

        @NotBlank(message = "informe o CPF do cliente")
        @Pattern(regexp = "\\d{11}", message = "o CPF deve ter 11 números, sem pontos, traços ou letras")
        String cpf,

        @NotBlank(message = "informe o e-mail do cliente")
        @Email(message = "informe um e-mail válido")
        String email,

        @NotBlank(message = "informe o telefone do cliente")
        @Size(min = 8, max = 20, message = "informe um telefone válido")
        String telefone
) {
}