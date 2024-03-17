package com.jeanlima.springmvcapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jeanlima.springmvcapp.model.Aluno;

@Component
public class AlunoServiceImpl implements  AlunoService{

    public List<Aluno> alunos = new ArrayList<Aluno>();
    private MockDataService data = new MockDataService();   

    @Override
    public void salvarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
        int id = alunos.size() + 1;
        aluno.setId(id);
        try{
            this.alunos.add(aluno);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        
        
    }

    @Override
    public void deletarAluno(Integer id) {
        Aluno alunoToDelete = new Aluno();
        for (Aluno aluno : alunos) {
            if(aluno.getId() == id){
                alunoToDelete = aluno;
                break;
            }
        }

        this.alunos.remove(alunoToDelete);
        
    }

    @Override
    public Aluno getAlunoById(Integer id) {
        for (Aluno aluno : alunos) {
            if(aluno.getId() == id){
                return aluno;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> getListaAluno() {
        return this.alunos;
    }

    @Override
    public Map<String, List<Aluno>> getListaAlunoByCurso() {
        String[] cursos = data.getCursos();
        Map<String, List<Aluno>> result = new HashMap<String, List<Aluno>>();

        for (String curso : cursos) {
            List<Aluno> alunosByCurso = alunos.stream().filter(a -> a.getCurso().equals(curso)).toList();
            result.put(curso, alunosByCurso);
        }
        
        return result;
    }

    @Override
    public Map<String, List<Aluno>> getListaAlunoBySistemaOperacional() {
        String[] sistemasOperacionais = data.getSistemasOperacionais();
        Map<String, List<Aluno>> result = new HashMap<String, List<Aluno>>();

        for (String so : sistemasOperacionais) {
            List<Aluno> alunosBySO = alunos.stream().filter(a -> a.getSistemasOperacionas().contains(so)).toList();
            result.put(so, alunosBySO);
        }
        
        return result;
    }

    @Override
    public Map<String, List<Aluno>> getListaAlunoByLinguagem() {
        String[] linguagens = data.getLinguagens();
        Map<String, List<Aluno>> result = new HashMap<String, List<Aluno>>();

        for (String linguagem : linguagens) {
            List<Aluno> alunosByLingugagem = alunos.stream().filter(a -> a.getLinguagem().equals(linguagem)).toList();
            result.put(linguagem, alunosByLingugagem);
        }
        
        return result;
    }

    
}
