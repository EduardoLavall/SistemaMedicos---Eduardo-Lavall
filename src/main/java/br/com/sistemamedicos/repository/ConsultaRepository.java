package br.com.sistemamedicos.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamedicos.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    //existe medico com aquele horario marcado
    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    //existe medico com aquele horario marcado, ignorando a propria consulta
    boolean existsByDoctorIdAndAppointmentDateAndIdNot(Long doctorId, LocalDateTime appointmentDate, Long id);

    //existe paciente com aquele horario marcado
    boolean existsByPatientIdAndAppointmentDate(Long patientId, LocalDateTime appointmentDate);

    //existe paciente com aquele horario marcado, ignorando a propria consulta
    boolean existsByPatientIdAndAppointmentDateAndIdNot(Long patientId, LocalDateTime appointmentDate, Long id);

}
