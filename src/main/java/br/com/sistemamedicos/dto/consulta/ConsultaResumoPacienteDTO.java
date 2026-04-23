package br.com.sistemamedicos.dto.consulta;

public record ConsultaResumoPacienteDTO(
        Long id,
        String name,
        String email,
        String cpf
) {
}
