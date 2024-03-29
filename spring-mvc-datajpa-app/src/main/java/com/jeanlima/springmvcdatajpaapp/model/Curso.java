package com.jeanlima.springmvcdatajpaapp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;


@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String descricao;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Aluno> alunos = new HashSet<Aluno>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name="disciplina_curso",
            joinColumns=@JoinColumn(name="disciplina_id"),
            inverseJoinColumns=@JoinColumn(name="curso_id"))
    private List<Disciplina> disciplinas;


    public Curso() {
    }
    
    public Curso(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Curso [descricao=" + descricao + ", id=" + id + "]";
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        return true;
    }

   
    
    
    
    
}
