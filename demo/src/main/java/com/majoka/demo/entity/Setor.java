package com.majoka.demo.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Entity
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Long id;

    @Column(nullable = false)
    private String  nome;

    @OneToMany(mappedBy = "setor")
    private List<Funcionario> funcionario;

    public Setor(){}
    public Setor(String nome){
        this.nome = nome;
    }
    public Long getId() {
        return id;
    }

    public String getNome(){
        return nome;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

}