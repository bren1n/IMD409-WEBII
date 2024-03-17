package com.jeanlima.springmvcapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.springmvcapp.model.Curso;
import com.jeanlima.springmvcapp.service.CursoService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping("/getListaCursos")
    public String showListaCurso(Model model) {
        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }

    @RequestMapping("/showForm")
    public String showFormCurso(Model model){

        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    }
    
    @RequestMapping("/addCurso")
    public String showFormCurso(@ModelAttribute("curso") Curso curso,  Model model) {
        cursoService.salvarCurso(curso);
        model.addAttribute("curso", curso);
        return "redirect:/curso/getListaCursos";
    }
    
}
