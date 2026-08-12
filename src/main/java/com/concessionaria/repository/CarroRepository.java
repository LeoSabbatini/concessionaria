package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    boolean existsByChassi(String chassi);

    boolean existsByChassiAndIdNot(String chassi, Integer id);

    boolean existsByPlaca(String placa);

    boolean existsByPlacaAndIdNot(String placa, Integer id);

    /*@Query("""
            SELECT c FROM Carro c
            WHERE
            """)
    List<Carro> buscarComFiltro(@Param("cor") String cor);
    */
}