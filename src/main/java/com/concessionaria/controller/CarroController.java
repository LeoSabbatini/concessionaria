package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public Carro cadastrar(@Valid @RequestBody Carro carro){
        return carroRepository.save(carro);
    }

    @GetMapping
    public List<Carro> carrosAll(){
        return carroRepository.findAll();
    }
    @GetMapping("/{id}")
    public Carro buscarPorId(@PathVariable Integer id) {
        return carroRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Carro atualizar(@PathVariable Integer id, @RequestBody Carro carroAtualizado) {
        carroRepository.findById(id).orElse(null);
        return carroRepository.save(carroAtualizado);
    }
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        carroRepository.findById(id)
                .orElse(null);
        carroRepository.deleteById(id);
    }



}
