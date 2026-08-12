package com.concessionaria.exception;

import com.concessionaria.dto.CampoErroDTO;
import com.concessionaria.dto.ErroResponseDTO;
import com.concessionaria.dto.ErroValidacaoResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponseDTO> handleValidacao(MethodArgumentNotValidException ex) {
        List<CampoErroDTO> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> new CampoErroDTO(erro.getField(), erro.getDefaultMessage()))
                .toList();

        ErroValidacaoResponseDTO corpo = new ErroValidacaoResponseDTO(HttpStatus.BAD_REQUEST.value(), erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpo);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> handleNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResponseDTO corpo = new ErroResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ErroResponseDTO> handleDuplicado(RegistroDuplicadoException ex) {
        ErroResponseDTO corpo = new ErroResponseDTO(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpo);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponseDTO> handleIntegridade(DataIntegrityViolationException ex) {
        ErroResponseDTO corpo = new ErroResponseDTO(HttpStatus.CONFLICT.value(),
                "já existe um registro com unique (placa, chassi, CPF, e-mail ou telefone)");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpo);
    }
}