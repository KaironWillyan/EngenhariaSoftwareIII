package com.majoka.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_setor")
    private Setor setor;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String tipoCnh;

    @Column(nullable = false)
    private String telefone;

    public Funcionario(){}

    public  Funcionario(String nome, String cpf, String tipoCnh, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCnh = tipoCnh;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Setor getSetor(){
        return setor;
    }

    public void setSetores(Setor setor){
        this.setor = setor;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public  String getTelefone(){
        return  telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getTipoCnh() {
        return tipoCnh;
    }

    public void setTipoCnh(String tipoCnh) {
        this.tipoCnh = tipoCnh;
    }

}