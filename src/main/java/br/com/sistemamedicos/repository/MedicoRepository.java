package br.com.sistemamedicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedicos.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
