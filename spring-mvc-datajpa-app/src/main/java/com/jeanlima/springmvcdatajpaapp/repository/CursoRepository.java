package com.jeanlima.springmvcdatajpaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.springmvcdatajpaapp.model.Curso;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CursoRepository extends JpaRepository<Curso,Integer>{

    @Query(value = "select c from Curso c")
    List<Curso> getAllCursos();
    
}
