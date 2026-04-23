package br.com.sistemamedicos.dto.consulta;

import br.com.sistemamedicos.enums.StatusConsulta;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull(message = "Paciente e obrigatorio")
        Long patientId,

        @NotNull(message = "Medico e obrigatorio")
        Long doctorId,

        @NotNull(message = "Data da consulta e obrigatoria")
        LocalDateTime appointmentDate,

        @NotNull(message = "Status da consulta e obrigatorio")
        StatusConsulta status,

        @NotNull(message = "Preco da consulta e obrigatorio")
        BigDecimal price,

        String notes
) {
}
