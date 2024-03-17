package com.jeanlima.springmvcapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jeanlima.springmvcapp.model.Aluno;

@Service
public interface AlunoService {

    public void salvarAluno(Aluno aluno);
    public void deletarAluno(Integer id);
    public Aluno getAlunoById(Integer id);
    public List<Aluno> getListaAluno();
    public Map<String, List<Aluno>> getListaAlunoByCurso();
    public Map<String, List<Aluno>> getListaAlunoBySistemaOperacional();
    public Map<String, List<Aluno>> getListaAlunoByLinguagem();

}
