package com.concessionaria.controller;

import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/lista")
    public List<Cliente> clientesAll(){
        return clienteRepository.findAll();
    }
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        clienteRepository.findById(id).orElse(null);
        return clienteRepository.save(clienteAtualizado);
    }
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        clienteRepository.findById(id)
                .orElse(null);
        clienteRepository.deleteById(id);
    }


}

