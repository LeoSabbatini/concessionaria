package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    boolean existsByChassi(String chassi);

    boolean existsByChassiAndIdNot(String chassi, Integer id);

    boolean existsByPlaca(String placa);

    boolean existsByPlacaAndIdNot(String placa, Integer id);

}