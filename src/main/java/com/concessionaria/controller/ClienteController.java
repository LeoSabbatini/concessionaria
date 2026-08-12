package com.concessionaria.controller;

import com.concessionaria.dto.ClienteRequestDTO;
import com.concessionaria.dto.ClienteResponseDTO;
import com.concessionaria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ClienteResponseDTO cadastrar(@Valid @RequestBody ClienteRequestDTO cliente){
        return clienteService.cadastrar(cliente);
    }

    @GetMapping
    public List<ClienteResponseDTO> clientesAll(){
        return clienteService.listarTodos();
    }
    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id);
    }
    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Integer id, @RequestBody ClienteRequestDTO clienteAtualizado) {
        return clienteService.atualizar(id, clienteAtualizado);
    }
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        clienteService.remover(id);
    }
}