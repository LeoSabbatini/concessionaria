package com.concessionaria.controller;

import com.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carros", description = "Consulta e cadastroo do estoque de carros")
@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @Operation(
            summary = "Cadastra um carro",
            description = "Cadastra um novo carro no estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados inválidos."),
            @ApiResponse(responseCode = "409", description = "Já existe um carro cadastrado com esse chassi ou placa")
    })
    @PostMapping
    public CarroResponseDTO cadastrar(@Valid @RequestBody CarroRequestDTO carro) {
        return carroService.cadastrar(carro);
    }

    //fazer o filtro pela cor e anoFabrica la
    @GetMapping
    public List<CarroResponseDTO> carrosTodos() {
        return carroService.listarTodos();
    }

    @Operation(summary = "Busca um carro pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro encontrado"),
            @ApiResponse(responseCode = "404", description = "Não tem carro com esse id")
    })
    @GetMapping("/{id}")
    public CarroResponseDTO buscarPorId(@PathVariable Integer id) {
        return carroService.buscarPorId(id);
    }

    @Operation(summary = "Atualiza um carro existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Não tem carro com esse id"),
            @ApiResponse(responseCode = "409", description = "Já existe um carro cadastrado com esse chassi ou placa")
    })
    @PutMapping("/{id}")
    public CarroResponseDTO atualizar(@PathVariable Integer id, @Valid @RequestBody CarroRequestDTO carroAtualizado) {
        return carroService.atualizar(id, carroAtualizado);
    }

    @Operation(summary = "Remove um carro do estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não tem carro com esse id")
    })
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        carroService.remover(id);
    }
}