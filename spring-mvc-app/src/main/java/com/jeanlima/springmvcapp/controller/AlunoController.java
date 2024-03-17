package com.jeanlima.springmvcapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.service.AlunoService;
import com.jeanlima.springmvcapp.service.MockDataService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/aluno")
public class AlunoController {


    @Autowired
    @Qualifier("alunoServiceImpl")
    AlunoService alunoService;

    @Autowired
    MockDataService mockDataService;


    @RequestMapping("/showForm")
    public String showFormAluno(Model model){

        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", mockDataService.getCursos());
        model.addAttribute("sistemasOperacionais", mockDataService.getSistemasOperacionais());
        model.addAttribute("linguagens", mockDataService.getLinguagens());
        return "aluno/formAluno";
    }

    @RequestMapping("/addAluno")
    public String showFormAluno(@ModelAttribute("aluno") Aluno aluno,  Model model){

        alunoService.salvarAluno(aluno);
        model.addAttribute("aluno", aluno);
        return "aluno/paginaAluno";
    }

    @RequestMapping("/getListaAlunos")
    public String showListaAluno(Model model){

        List<Aluno> alunos = alunoService.getListaAluno();
        model.addAttribute("alunos",alunos);
        return "aluno/listaAlunos";

    }

    @RequestMapping("/getListaAlunosByCurso")
    public String showListaAlunosByCurso(Model model) {
        Map<String, List<Aluno>> alunos = alunoService.getListaAlunoByCurso();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunosByCurso";
    }
    
    @RequestMapping("/getListaAlunosBySistemaOperacional")
    public String showListaAlunosBySistemaOperacional(Model model) {
        Map<String, List<Aluno>> alunos = alunoService.getListaAlunoBySistemaOperacional();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunosBySistemaOperacional";
    }

    @RequestMapping("/getListaAlunosByLinguagem")
    public String showListaAlunosByLinguagem(Model model) {
        Map<String, List<Aluno>> alunos = alunoService.getListaAlunoByLinguagem();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunosByLinguagem";
    }

    @RequestMapping("/deleteAluno/{id}")
    public String deleteAluno(@PathVariable int id) {
        alunoService.deletarAluno(id);
        return "redirect:/aluno/getListaAlunos";
    }

    @RequestMapping("/getAluno/{id}")
    public String getAluno(@PathVariable int id, Model model) {
        Aluno aluno = alunoService.getAlunoById(id);
        model.addAttribute("aluno", aluno);
        return "aluno/detailAluno";
    }
    
    
}
