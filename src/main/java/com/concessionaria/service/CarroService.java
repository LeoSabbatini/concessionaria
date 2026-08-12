package com.concessionaria.service;

import com.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.dto.ClienteResumoDTO;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Carro;
import com.concessionaria.model.Cliente;
import com.concessionaria.model.Disponibilidade;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroResponseDTO cadastrar(CarroRequestDTO dto) {
        if (carroRepository.existsByChassi(dto.chassi())) {
            throw new RegistroDuplicadoException("já existe um carro cadastrado com esse chassi");
        }
        if (dto.placa() != null && carroRepository.existsByPlaca(dto.placa())) {
            throw new RegistroDuplicadoException("já existe um carro cadastrado com essa placa");
        }

        Carro carro = new Carro();
        preencherCamposEditaveis(carro, dto);
        carro.setDisponibilidade(Disponibilidade.DISPONIVEL);

        Carro salvo = carroRepository.save(carro);
        return toResponseDTO(salvo);
    }

    public List<CarroResponseDTO> listarTodos() {
        return carroRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CarroResponseDTO buscarPorId(Integer id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("carro com id " + id + " não encontrado"));
        return toResponseDTO(carro);
    }

    public CarroResponseDTO atualizar(Integer id, CarroRequestDTO dto) {
        Carro existente = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("carro com id " + id + " não encontrado"));

        if (carroRepository.existsByChassiAndIdNot(dto.chassi(), id)) {
            throw new RegistroDuplicadoException("já existe outro carro cadastrado com esse chassi");
        }
        if (dto.placa() != null && carroRepository.existsByPlacaAndIdNot(dto.placa(), id)) {
            throw new RegistroDuplicadoException("já existe outro carro cadastrado com essa placa");
        }

        preencherCamposEditaveis(existente, dto);

        Carro atualizado = carroRepository.save(existente);
        return toResponseDTO(atualizado);
    }

    public void remover(Integer id) {
        if (!carroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("carro com id " + id + " não encontrado");
        }
        carroRepository.deleteById(id);
    }

    private void preencherCamposEditaveis(Carro carro, CarroRequestDTO dto) {
        carro.setModelo(dto.modelo());
        carro.setMarca(dto.marca());
        carro.setAnoFabrica(dto.anoFabrica());
        carro.setAnoModelo(dto.anoModelo());
        carro.setPreco(dto.preco());
        carro.setCor(dto.cor());
        carro.setPlaca(dto.placa());
        carro.setChassi(dto.chassi());
        carro.setQuilometragem(dto.quilometragem());
        carro.setCondicao(dto.condicao());
    }

    private CarroResponseDTO toResponseDTO(Carro carro) {
        Cliente cliente = carro.getCliente();
        ClienteResumoDTO clienteResumo = cliente == null
                ? null
                : new ClienteResumoDTO(cliente.getId(), cliente.getNome());

        return new CarroResponseDTO(
                carro.getId(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getAnoFabrica(),
                carro.getAnoModelo(),
                carro.getPreco(),
                carro.getCor(),
                carro.getPlaca(),
                carro.getChassi(),
                carro.getQuilometragem(),
                carro.getCondicao(),
                carro.getDisponibilidade(),
                clienteResumo
        );
    }
}