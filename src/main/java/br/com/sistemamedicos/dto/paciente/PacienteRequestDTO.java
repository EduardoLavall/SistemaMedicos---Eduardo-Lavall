package br.com.sistemamedicos.dto.paciente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PacienteRequestDTO(
        @NotBlank(message = "Nome é obrigatorio")
        String name,

        @Email(message = "Email invalido")
        @NotBlank(message = "Email é obrigatorio")
        String email,

        @NotBlank(message = "CPF é obrigatorio")
        String cpf
) {
}
