package br.com.sistemamedicos.dto.medico;

public record MedicoResponseDTO(
        Long id,
        String name,
        String speciality,
        String crm
) {
}
