package br.com.sistemamedicos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String name;

// email obrigatorio e nao pode repetir.
@Column(nullable = false, unique = true)
private String email;

@Column(nullable = false, unique = true, length = 14)
private String cpf;

// lado 1:N, ou seja, 1 paciente pode ter varias consultas.
@OneToMany(mappedBy = "patient")
private List<Consulta> consultas = new ArrayList<>();

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

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public List<Consulta> getConsultas() {
    return consultas;
}

public void setConsultas(List<Consulta> consultas) {
    this.consultas = consultas;
}

}
