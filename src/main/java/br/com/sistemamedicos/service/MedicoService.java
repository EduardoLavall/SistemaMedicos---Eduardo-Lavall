package br.com.sistemamedicos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistemamedicos.dto.medico.MedicoRequestDTO;
import br.com.sistemamedicos.dto.medico.MedicoResponseDTO;
import br.com.sistemamedicos.entity.Medico;
import br.com.sistemamedicos.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
    // deixei a conversao pra DTO centralizada pra evitar repeticao.
    private MedicoResponseDTO toResponseDTO(Medico medico) {
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getName(),
                medico.getSpeciality(),
                medico.getCrm()
        );
    }
    public List<MedicoResponseDTO> listarTodos() {
        return medicoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    // se nao achar o medico pelo id, eu lanço erro.
    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico nao encontrado"));
    
        return toResponseDTO(medico);
    }
    //create
    public MedicoResponseDTO criar(MedicoRequestDTO requestDTO) {
        Medico medico = new Medico();
        medico.setName(requestDTO.name());
        medico.setSpeciality(requestDTO.speciality());
        medico.setCrm(requestDTO.crm());
    
        Medico medicoSalvo = medicoRepository.save(medico);
        return toResponseDTO(medicoSalvo);
    }
    //update
    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO requestDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico nao encontrado"));
    
        medico.setName(requestDTO.name());
        medico.setSpeciality(requestDTO.speciality());
        medico.setCrm(requestDTO.crm());
    
        Medico medicoAtualizado = medicoRepository.save(medico);
        return toResponseDTO(medicoAtualizado);
    }
    //delete
    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }
    
    
    
    
}
