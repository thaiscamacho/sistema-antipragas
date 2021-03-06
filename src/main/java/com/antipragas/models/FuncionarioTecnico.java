package com.antipragas.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "funcionarios_tecnicos")
public class FuncionarioTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @Expose
    private String nome;

    public FuncionarioTecnico() {
    }

    public FuncionarioTecnico(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
