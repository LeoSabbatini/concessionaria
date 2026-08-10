package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.CarroRepository;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

}

