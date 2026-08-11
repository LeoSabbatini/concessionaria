package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import java.math.BigDecimal;

public record CarroRequestDTO(
        String modelo,
        String marca,
        Integer anoFabrica,
        Integer anoModelo,
        BigDecimal preco,
        String cor,
        String placa,
        String chassi,
        Integer quilometragem,
        Condicao condicao
) {
}