package com.elotech.cadastropessoa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotBlank(message = "CPF é obrigatório.")
    @Size(max = 11, message = "CPF deve conter 11 dígitos.")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datanascimento;

    @NotBlank(message = "Email é obrigatório.")
    @Column(unique=true)
    private String email;

    @NotBlank(message = "Senha é obrigatório.")
    private String senha;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("pessoa")
    private List<ContatoModel> contato;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(LocalDate datanascimento) {
        this.datanascimento = datanascimento;
    }

    public List<ContatoModel> getContato() {
        return contato;
    }

    public void setContato(List<ContatoModel> contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
