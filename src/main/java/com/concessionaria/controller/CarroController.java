package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroRepository carroRepository;

    @PostMapping("/cadastrar")
    public Carro cadastrar(@RequestBody Carro carro){
        return carroRepository.save(carro);
    }

    @GetMapping("/all")
    public List<Carro> carrosAll(){
        return carroRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Carro> carrosPorCliente(){
        return carroRepository.findByCliente(null);
    }


}
