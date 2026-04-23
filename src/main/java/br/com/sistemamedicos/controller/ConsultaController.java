package br.com.sistemamedicos.controller;

import br.com.sistemamedicos.dto.consulta.ConsultaRequestDTO;
import br.com.sistemamedicos.dto.consulta.ConsultaResponseDTO;
import br.com.sistemamedicos.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {
    // esse controller recebe as requisicoes do CRUD de consulta.

    private final ConsultaService consultaService;

    //base
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    //lista
    // endpoint pra listar todas as consultas.
    @GetMapping
public List<ConsultaResponseDTO> listarTodos() {
    return consultaService.listarTodos();
}

//busca pelo id
// endpoint pra buscar 1 consulta especifica.
@GetMapping("/{id}")
public ConsultaResponseDTO buscarPorId(@PathVariable Long id) {
    return consultaService.buscarPorId(id);
}

//create
// cadastro da consulta usando os ids de paciente e medico do DTO.
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public ConsultaResponseDTO criar(@RequestBody @Valid ConsultaRequestDTO requestDTO) {
    return consultaService.criar(requestDTO);
}

//update
// atualizacao completa da consulta pelo id.
@PutMapping("/{id}")
public ConsultaResponseDTO atualizar(@PathVariable Long id,
                                     @RequestBody @Valid ConsultaRequestDTO requestDTO) {
    return consultaService.atualizar(id, requestDTO);
}

//delete
// exclusao da consulta pelo id.
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletar(@PathVariable Long id) {
    consultaService.deletar(id);
}




}
