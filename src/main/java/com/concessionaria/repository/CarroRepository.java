package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Query("""
            SELECT new com.concessionaria.model.Carro
            """)
    List<Carro> carroPorCliente();
}

