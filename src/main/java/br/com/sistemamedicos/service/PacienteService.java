package br.com.sistemamedicos.service;

import br.com.sistemamedicos.dto.paciente.PacienteRequestDTO;
import br.com.sistemamedicos.dto.paciente.PacienteResponseDTO;
import br.com.sistemamedicos.entity.Paciente;
import br.com.sistemamedicos.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    private PacienteResponseDTO toResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getName(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }

    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // se o id nao existir, eu paro o fluxo com erro.
    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));

        return toResponseDTO(paciente);
    }

    // no create eu monto a entity com o DTO e salvo no banco.
    public PacienteResponseDTO criar(PacienteRequestDTO requestDTO) {
        Paciente paciente = new Paciente();
        paciente.setName(requestDTO.name());
        paciente.setEmail(requestDTO.email());
        paciente.setCpf(requestDTO.cpf());

        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return toResponseDTO(pacienteSalvo);
    }

    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO requestDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));

        paciente.setName(requestDTO.name());
        paciente.setEmail(requestDTO.email());
        paciente.setCpf(requestDTO.cpf());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return toResponseDTO(pacienteAtualizado);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
