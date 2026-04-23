package br.com.sistemamedicos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistemamedicos.dto.consulta.ConsultaRequestDTO;
import br.com.sistemamedicos.dto.consulta.ConsultaResponseDTO;
import br.com.sistemamedicos.dto.consulta.ConsultaResumoMedicoDTO;
import br.com.sistemamedicos.dto.consulta.ConsultaResumoPacienteDTO;
import br.com.sistemamedicos.entity.Consulta;
import br.com.sistemamedicos.entity.Medico;
import br.com.sistemamedicos.entity.Paciente;
import br.com.sistemamedicos.repository.ConsultaRepository;
import br.com.sistemamedicos.repository.MedicoRepository;
import br.com.sistemamedicos.repository.PacienteRepository;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository
    ) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    // esse metodo monta a resposta completa da consulta com patient e doctor.
    private ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                new ConsultaResumoPacienteDTO(
                        consulta.getPatient().getId(),
                        consulta.getPatient().getName(),
                        consulta.getPatient().getEmail(),
                        consulta.getPatient().getCpf()
                ),
                new ConsultaResumoMedicoDTO(
                        consulta.getDoctor().getId(),
                        consulta.getDoctor().getName(),
                        consulta.getDoctor().getSpeciality(),
                        consulta.getDoctor().getCrm()
                ),
                consulta.getAppointmentDate(),
                consulta.getStatus(),
                consulta.getPrice(),
                consulta.getNotes()
        );
    }

    private void validarConflitoHorarioMedicoNoCreate(Long doctorId, LocalDateTime appointmentDate) {
        if (consultaRepository.existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate)) {
            throw new RuntimeException("O medico ja possui consulta nesse horario");
        }
    }

    private void validarConflitoHorarioPacienteNoCreate(Long patientId, LocalDateTime appointmentDate) {
        if (consultaRepository.existsByPatientIdAndAppointmentDate(patientId, appointmentDate)) {
            throw new RuntimeException("O paciente ja possui consulta nesse horario");
        }
    }

    private void validarConflitoHorarioMedicoNoUpdate(Long doctorId, LocalDateTime appointmentDate, Long consultaId) {
        if (consultaRepository.existsByDoctorIdAndAppointmentDateAndIdNot(doctorId, appointmentDate, consultaId)) {
            throw new RuntimeException("O medico ja possui consulta nesse horario");
        }
    }

    private void validarConflitoHorarioPacienteNoUpdate(Long patientId, LocalDateTime appointmentDate, Long consultaId) {
        if (consultaRepository.existsByPatientIdAndAppointmentDateAndIdNot(patientId, appointmentDate, consultaId)) {
            throw new RuntimeException("O paciente ja possui consulta nesse horario");
        }
    }

    private void validarDataNoPassado(LocalDateTime appointmentDate) {
        if (appointmentDate.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Nao e permitido agendar consulta no passado");
        }
    }

    private void validarPreco(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor da consulta deve ser maior que zero");
        }
    }

    public List<ConsultaResponseDTO> listarTodos() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta nao encontrada"));

        return toResponseDTO(consulta);
    }

    // antes de salvar a consulta eu busco paciente e medico pelos ids do DTO.
    public ConsultaResponseDTO criar(ConsultaRequestDTO requestDTO) {
        Paciente paciente = pacienteRepository.findById(requestDTO.patientId())
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));

        Medico medico = medicoRepository.findById(requestDTO.doctorId())
                .orElseThrow(() -> new RuntimeException("Medico nao encontrado"));

        validarConflitoHorarioMedicoNoCreate(requestDTO.doctorId(), requestDTO.appointmentDate());
        validarConflitoHorarioPacienteNoCreate(requestDTO.patientId(), requestDTO.appointmentDate());
        validarDataNoPassado(requestDTO.appointmentDate());
        validarPreco(requestDTO.price());

        Consulta consulta = new Consulta();
        consulta.setPatient(paciente);
        consulta.setDoctor(medico);
        consulta.setAppointmentDate(requestDTO.appointmentDate());
        consulta.setStatus(requestDTO.status());
        consulta.setPrice(requestDTO.price());
        consulta.setNotes(requestDTO.notes());

        Consulta consultaSalva = consultaRepository.save(consulta);
        return toResponseDTO(consultaSalva);
    }

    // na atualizacao eu valido a propria consulta e tbm os ids de paciente e medico.
    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO requestDTO) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta nao encontrada"));

        Paciente paciente = pacienteRepository.findById(requestDTO.patientId())
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));

        Medico medico = medicoRepository.findById(requestDTO.doctorId())
                .orElseThrow(() -> new RuntimeException("Medico nao encontrado"));

        validarConflitoHorarioMedicoNoUpdate(requestDTO.doctorId(), requestDTO.appointmentDate(), id);
        validarConflitoHorarioPacienteNoUpdate(requestDTO.patientId(), requestDTO.appointmentDate(), id);
        validarDataNoPassado(requestDTO.appointmentDate());
        validarPreco(requestDTO.price());

        consulta.setPatient(paciente);
        consulta.setDoctor(medico);
        consulta.setAppointmentDate(requestDTO.appointmentDate());
        consulta.setStatus(requestDTO.status());
        consulta.setPrice(requestDTO.price());
        consulta.setNotes(requestDTO.notes());

        Consulta consultaAtualizada = consultaRepository.save(consulta);
        return toResponseDTO(consultaAtualizada);
    }

    // remocao direta da consulta por id.
    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }
}
