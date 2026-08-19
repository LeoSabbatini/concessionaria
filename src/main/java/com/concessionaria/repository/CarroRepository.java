package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    boolean existsByChassi(String chassi);

    boolean existsByChassiAndIdNot(String chassi, Integer id);

    boolean existsByPlaca(String placa);

    boolean existsByPlacaAndIdNot(String placa, Integer id);

    List<Carro> findByCor(String cor);
    List<Carro> findByAnoFabrica(Integer ano);

}