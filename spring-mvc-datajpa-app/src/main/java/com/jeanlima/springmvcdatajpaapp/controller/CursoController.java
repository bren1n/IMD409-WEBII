package com.jeanlima.springmvcdatajpaapp.controller;

import com.jeanlima.springmvcdatajpaapp.model.Aluno;
import com.jeanlima.springmvcdatajpaapp.model.Curso;
import com.jeanlima.springmvcdatajpaapp.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @RequestMapping("/getListaCursos")
    public String showListaCurso(Model model) {
        List<Curso> cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }

    @RequestMapping(value = {"showForm", "/showForm/{id}"})
    public String showFormCurso(@PathVariable(required = false) Integer id, Model model) {
        if (id == null) {
            model.addAttribute("curso", new Curso());
            model.addAttribute("action", "create");
        } else {
            model.addAttribute("curso", cursoService.getCursoById(id));
            model.addAttribute("action", "edit");
        }

        return "curso/formCurso";
    }

    @RequestMapping("/addCurso")
    public String addCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.createCurso(curso);
        return "redirect:/curso/getListaCursos";
    }

    @RequestMapping("/editCurso/{id}")
    public String editCurso(@ModelAttribute("curso") Curso curso, @PathVariable int id) {
        cursoService.updateCurso(curso.getId(), curso.getDescricao());
        return "redirect:/curso/getListaCursos";
    }

    @RequestMapping("/deleteCurso/{id}")
    public String deleteCurso(@PathVariable int id) {
        cursoService.deleteCursoById(id);
        return "redirect:/curso/getListaCursos";
    }
}
