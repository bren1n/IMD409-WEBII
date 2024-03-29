package com.jeanlima.springmvcdatajpaapp.controller;

import com.jeanlima.springmvcdatajpaapp.model.Curso;
import com.jeanlima.springmvcdatajpaapp.model.Disciplina;
import com.jeanlima.springmvcdatajpaapp.service.CursoService;
import com.jeanlima.springmvcdatajpaapp.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {
    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    CursoService cursoService;

    @RequestMapping("/showForm")
    public String showFormDisciplina(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("cursos", cursoService.getCursos());

        return "disciplina/formDisciplina";
    }

    @RequestMapping("/addDisciplina")
    public String addDisciplina(@ModelAttribute("disciplina") Disciplina disciplina, @RequestParam("cursoIds") List<Integer> cursoIds) {
        List<Curso> cursosSelecionados = cursoService.getCursosByIds(cursoIds);
        disciplina.setCursos(cursosSelecionados);

        disciplinaService.salvarDisciplina(disciplina);

        return "redirect:/";
    }

    @RequestMapping("/getListaDisciplinas")
    public String showListaDisciplina(Model model) {
        List<Disciplina> disciplinas = disciplinaService.getDisciplinas();
        model.addAttribute("disciplinas", disciplinas);

        return "disciplina/listaDisciplina";
    }
}
