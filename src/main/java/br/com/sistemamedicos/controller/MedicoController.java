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

import br.com.sistemamedicos.dto.medico.MedicoRequestDTO;
import br.com.sistemamedicos.dto.medico.MedicoResponseDTO;
import br.com.sistemamedicos.service.MedicoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    // esse controller expoe os endpoints REST do medico.

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    // lista todos os medicos.
    @GetMapping
public List<MedicoResponseDTO> listarTodos() {
    return medicoService.listarTodos();
}
// busca 1 medico especifico pelo id.
@GetMapping("/{id}")
public MedicoResponseDTO buscarPorId(@PathVariable Long id) {
    return medicoService.buscarPorId(id);
}
//create
// cadastra medico novo e ja devolve o DTO de resposta.
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public MedicoResponseDTO criar(@RequestBody @Valid MedicoRequestDTO requestDTO) {
    return medicoService.criar(requestDTO);
}
//update
// atualiza medico existente pelo id.
@PutMapping("/{id}")
public MedicoResponseDTO atualizar(@PathVariable Long id,
                                   @RequestBody @Valid MedicoRequestDTO requestDTO) {
    return medicoService.atualizar(id, requestDTO);
}
//delete
// remove o medico pelo id.
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deletar(@PathVariable Long id) {
    medicoService.deletar(id);
}




}
