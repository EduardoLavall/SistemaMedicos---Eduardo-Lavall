package br.com.sistemamedicos.dto.medico;

import jakarta.validation.constraints.NotBlank;

public record MedicoRequestDTO(
        @NotBlank(message = "Nome e obrigatorio")
        String name,

        @NotBlank(message = "Especialidade e obrigatoria")
        String speciality,

        @NotBlank(message = "CRM e obrigatorio")
        String crm
) {
}
