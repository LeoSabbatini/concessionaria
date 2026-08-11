package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import com.concessionaria.model.Disponibilidade;
import java.math.BigDecimal;

public record CarroResponseDTO(
        Integer id,
        String modelo,
        String marca,
        Integer anoFabrica,
        Integer anoModelo,
        BigDecimal preco,
        String cor,
        String placa,
        String chassi,
        Integer quilometragem,
        Condicao condicao,
        Disponibilidade disponibilidade,
        ClienteResumoDTO cliente
) {
}