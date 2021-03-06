package com.antipragas.models;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Thais Camacho
 */

@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Column(length = 9)
    @Expose
    private String cep;

    @Column(length = 60)
    @Expose
    private String rua;

    @Column(length = 40)
    @Expose
    private String bairro;

    @Column(length = 40)
    @Expose
    private String cidade;

    @Column(length = 13)
    @Expose
    private Integer numero;

    @Column(length = 60)
    private String complemento;

    @Column(length = 13)
    @Expose
    private String uf;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL)
    private Set<Proposta> propostas;

    public Endereco() {
    }

    public Endereco(String cep, String rua, String bairro, String cidade, Integer numero, String complemento, String uf) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
    }

    public Endereco(String cep, String rua, String bairro, String cidade, Integer numero, String complemento, String uf, Usuario usuario) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
