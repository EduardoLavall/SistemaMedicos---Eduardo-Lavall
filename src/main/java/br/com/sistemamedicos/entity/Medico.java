package br.com.sistemamedicos.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String name;

@Column(nullable = false)
private String speciality;

// crm unico porque nao faz sentido repetir.
@Column(nullable = false, unique = true)
private String crm;

@OneToMany(mappedBy = "doctor")
private List<Consulta> consultas = new ArrayList<>();

//Getters e setters:

public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}


public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}


public String getSpeciality() {
    return speciality;
}
public void setSpeciality(String speciality) {
    this.speciality = speciality;
}


public String getCrm() {
    return crm;
}
public void setCrm(String crm) {
    this.crm = crm;
}


public List<Consulta> getConsultas() {
    return consultas;
}
public void setConsultas(List<Consulta> consultas) {
    this.consultas = consultas;
}


}
