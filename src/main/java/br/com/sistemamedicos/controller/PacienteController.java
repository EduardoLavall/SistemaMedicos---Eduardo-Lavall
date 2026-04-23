package br.com.sistemamedicos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemamedicos.dto.paciente.PacienteRequestDTO;
import br.com.sistemamedicos.dto.paciente.PacienteResponseDTO;
import br.com.sistemamedicos.service.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    // controller e a porta de entrada do CRUD de paciente.

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // GET de lista.
    @GetMapping
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteService.listarTodos();
    }

    // GET por id.
    @GetMapping("/{id}")
    public PacienteResponseDTO buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    // POST pra criar paciente usando DTO validado.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO criar(@RequestBody @Valid PacienteRequestDTO requestDTO) {
        return pacienteService.criar(requestDTO);
    }

    // PUT pra atualizar paciente existente.
    @PutMapping("/{id}")
    public PacienteResponseDTO atualizar(@PathVariable Long id,
                                         @RequestBody @Valid PacienteRequestDTO requestDTO) {
        return pacienteService.atualizar(id, requestDTO);
    }

    // DELETE pra remover pelo id.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}
