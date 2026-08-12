package com.concessionaria.controller;

import com.concessionaria.dto.ClienteRequestDTO;
import com.concessionaria.dto.ClienteResponseDTO;
import com.concessionaria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Cadastro e consulta dos clientes")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Cadastra um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Já existe um cliente cadastrado com esse CPF, e-mail ou telefone")
    })
    @PostMapping
    public ClienteResponseDTO cadastrar(@Valid @RequestBody ClienteRequestDTO cliente){
        return clienteService.cadastrar(cliente);
    }

    @Operation(summary = "Lista todos os clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "sucesso na lista")
    })
    @GetMapping
    public List<ClienteResponseDTO> clientesTodos(){
        return clienteService.listarTodos();
    }

    @Operation(summary = "Busca um cliente pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Não tem cliente com esse id")
    })
    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id);
    }

    @Operation(summary = "Atualiza um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Não tem cliente com esse id"),
            @ApiResponse(responseCode = "409", description = "informacoes ja em uso")
    })
    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Integer id, @RequestBody ClienteRequestDTO clienteAtualizado) {
        return clienteService.atualizar(id, clienteAtualizado);
    }

    @Operation(summary = "Remove um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não tem cliente com esse id")
    })
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        clienteService.remover(id);
    }
}