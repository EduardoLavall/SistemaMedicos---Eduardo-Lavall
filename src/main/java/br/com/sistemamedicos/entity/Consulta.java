package br.com.sistemamedicos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.sistemamedicos.enums.StatusConsulta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "consultas")
public class Consulta {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "patient_id", nullable = false)
private Paciente patient;

@ManyToOne(optional = false)
@JoinColumn(name = "doctor_id", nullable = false)
private Medico doctor;

@Column(nullable = false)
private LocalDateTime appointmentDate;

// o status vai ser salvo como texto pra ficar mais claro.
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private StatusConsulta status;

// usei BigDecimal porque e o tipo mais seguro pra dinheiro.
@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal price;

@Column(columnDefinition = "TEXT")
private String notes;

//getters e setters

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Paciente getPatient() {
    return patient;
}

public void setPatient(Paciente patient) {
    this.patient = patient;
}

public Medico getDoctor() {
    return doctor;
}

public void setDoctor(Medico doctor) {
    this.doctor = doctor;
}

public LocalDateTime getAppointmentDate() {
    return appointmentDate;
}

public void setAppointmentDate(LocalDateTime appointmentDate) {
    this.appointmentDate = appointmentDate;
}

public StatusConsulta getStatus() {
    return status;
}

public void setStatus(StatusConsulta status) {
    this.status = status;
}

public BigDecimal getPrice() {
    return price;
}

public void setPrice(BigDecimal price) {
    this.price = price;
}

public String getNotes() {
    return notes;
}

public void setNotes(String notes) {
    this.notes = notes;
}


}
