package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CarroRequestDTO(

        @NotBlank(message = "informe o modelo do carro")
        String modelo,

        @NotBlank(message = "informe a marca do carro")
        String marca,

        @NotNull(message = "informe o ano de fabricação")
        @Min(value = 1950, message = "ano de fabricação inválido")
        @Max(value = 2026, message = "ano de fabricação não pode ser maior que 2026")
        Integer anoFabrica,

        @NotNull(message = "informe o ano do modelo")
        @Min(value = 1950, message = "ano do modelo inválido")
        @Max(value = 2027, message = "ano do modelo não pode ser maior que 2027")
        Integer anoModelo,

        @NotNull(message = "informe o preço do carro")
        @Positive(message = "o preço deve ser maior que zero")
        BigDecimal preco,

        @NotBlank(message = "informe a cor do carro")
        String cor,

        String placa,

        @NotBlank(message = "informe o chassi do carro")
        @Size(min = 17, max = 17, message = "o chassi deve ter exatamente 17 caracteres")
        String chassi,

        @NotNull(message = "informe a quilometragem do carro")
        @PositiveOrZero(message = "a quilometragem não pode ser negativa")
        Integer quilometragem,

        @NotNull(message = "informe a condição do carro (NOVO ou SEMINOVO)")
        Condicao condicao
) {
}