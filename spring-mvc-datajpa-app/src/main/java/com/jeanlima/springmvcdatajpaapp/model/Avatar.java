package com.jeanlima.springmvcdatajpaapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 35)
    private String nomeFantasia;

    @OneToOne(mappedBy = "avatar")
    private Aluno aluno;

    public Avatar() { };

    public Avatar(String nomeFantasia, Aluno aluno) {
        this.nomeFantasia = nomeFantasia;
        this.aluno = aluno;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
