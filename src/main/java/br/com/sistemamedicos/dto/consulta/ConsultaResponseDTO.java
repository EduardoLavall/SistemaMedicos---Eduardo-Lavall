package br.com.sistemamedicos.dto.consulta;

import br.com.sistemamedicos.enums.StatusConsulta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        ConsultaResumoPacienteDTO patient,
        ConsultaResumoMedicoDTO doctor,
        LocalDateTime appointmentDate,
        StatusConsulta status,
        BigDecimal price,
        String notes
) {
}
