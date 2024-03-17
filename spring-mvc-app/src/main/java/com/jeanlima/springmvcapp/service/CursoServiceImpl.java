package com.jeanlima.springmvcapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeanlima.springmvcapp.model.Curso;


@Component
public class CursoServiceImpl implements CursoService {
    public List<Curso> cursos = new ArrayList<Curso>();


    @Override
    public void salvarCurso(Curso curso) {
        int id = cursos.size() + 1;
        curso.setId(id);

        try {
            this.cursos.add(curso);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        
    }

    @Override
    public List<Curso> getListaCurso() {
        Curso curso1 = new Curso(1, "Teste 1");
        Curso curso2 = new Curso(2, "Teste 2");

        this.cursos.add(curso1);
        this.cursos.add(curso2);
        return this.cursos;
    }
}
