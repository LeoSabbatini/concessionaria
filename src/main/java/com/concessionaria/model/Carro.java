package com.concessionaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Integer anoFabrica;

    @Column(nullable = false)
    private Integer anoModelo;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = true ,unique = true)
    private String placa;

    @Column(nullable = false,unique = true)
    private String chassi;

    @Column(nullable = true)
    private Integer quilometragem;

    @Column(nullable = false)
    private Condicao condicao;

    @Column(nullable = false)
    private Disponibilidade disponibilidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
