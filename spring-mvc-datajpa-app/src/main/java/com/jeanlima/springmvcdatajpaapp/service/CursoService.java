package com.jeanlima.springmvcdatajpaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.springmvcdatajpaapp.model.Curso;
import com.jeanlima.springmvcdatajpaapp.repository.CursoRepository;

@Component
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Integer id){
        return cursoRepository.findById(id).map(curso -> {
            return curso;
        }).orElseThrow(() -> null);
    }

    public void createCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public void updateCurso(Integer id, String descricao) {
        Curso curso = this.getCursoById(id);
        curso.setDescricao(descricao);
        cursoRepository.save(curso);
    }

    public void deleteCursoById(Integer id) {
        cursoRepository.deleteById(id);
    }
    
}
