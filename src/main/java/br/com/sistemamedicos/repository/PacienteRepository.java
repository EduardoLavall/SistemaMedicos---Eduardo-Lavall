package br.com.sistemamedicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedicos.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
