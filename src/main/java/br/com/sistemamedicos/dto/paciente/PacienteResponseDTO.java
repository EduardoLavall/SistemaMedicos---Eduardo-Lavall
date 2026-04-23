package br.com.sistemamedicos.dto.paciente;

public record PacienteResponseDTO(
        Long id,
        String name,
        String email,
        String cpf
) {
}
