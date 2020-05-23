package com.example.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Curso {
   
    @Id
    @GeneratedValue
    private Integer id;
    @Column(columnDefinition="text")
    private String nome;
    @Column(columnDefinition="text")
    private String duracao;
   
    public Curso() {
       
    }
       
    public Curso(Integer id, String nome, String duracao) {
        super();
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
    }
   
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDuracao() {
        return duracao;
    }
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
 
    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", duracao=" + duracao + "]";
    }
}