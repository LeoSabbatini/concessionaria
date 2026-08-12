package com.concessionaria.service;

import com.concessionaria.dto.ClienteRequestDTO;
import com.concessionaria.dto.ClienteResponseDTO;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        if (clienteRepository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException("já existe um cliente cadastrado com esse CPF");
        }
        if (clienteRepository.existsByEmail(dto.email())) {
            throw new RegistroDuplicadoException("já existe um cliente cadastrado com esse e-mail");
        }
        if (clienteRepository.existsByTelefone(dto.telefone())) {
            throw new RegistroDuplicadoException("já existe um cliente cadastrado com esse telefone");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        Cliente salvo = clienteRepository.save(cliente);
        return toResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("cliente com id " + id + " não encontrado"));
        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizar(Integer id, ClienteRequestDTO dto) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("cliente com id " + id + " não encontrado"));

        if (clienteRepository.existsByCpfAndIdNot(dto.cpf(), id)) {
            throw new RegistroDuplicadoException("já existe outro cliente cadastrado com esse CPF");
        }
        if (clienteRepository.existsByEmailAndIdNot(dto.email(), id)) {
            throw new RegistroDuplicadoException("já existe outro cliente cadastrado com esse e-mail");
        }
        if (clienteRepository.existsByTelefoneAndIdNot(dto.telefone(), id)) {
            throw new RegistroDuplicadoException("já existe outro cliente cadastrado com esse telefone");
        }

        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setEmail(dto.email());
        existente.setTelefone(dto.telefone());

        Cliente atualizado = clienteRepository.save(existente);
        return toResponseDTO(atualizado);
    }

    public void remover(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("cliente com id " + id + " não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}