package com.jeanlima.springmvcdatajpaapp.controller;

import com.jeanlima.springmvcdatajpaapp.model.Aluno;
import com.jeanlima.springmvcdatajpaapp.model.Avatar;
import com.jeanlima.springmvcdatajpaapp.service.AlunoService;
import com.jeanlima.springmvcdatajpaapp.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/avatar")
public class AvatarController {
    @Autowired
    AvatarService avatarService;

    @Autowired
    AlunoService alunoService;

    @RequestMapping("/getListaAvatares")
    public String showListaAvatar(Model model) {
        List<Avatar> avatares = avatarService.getAvatares();
        model.addAttribute("avatares", avatares);
        return "avatar/listaAvatar";
    }

    @RequestMapping("/showForm")
    public String showFormAvatar(Model model) {
        model.addAttribute("avatar", new Avatar());
        model.addAttribute("alunos", alunoService.getListaAluno());
        return "avatar/formAvatar";
    }

    @RequestMapping("/addAvatar")
    public String addAvatar(@ModelAttribute("avatar") Avatar avatar) {
        Aluno aluno = alunoService.getAlunoById(avatar.getAluno().getId());
        avatar.setAluno(aluno);
        aluno.setAvatar(avatar);
        avatarService.createAvatar(avatar);
        alunoService.salvarAluno(aluno);
        return "redirect:/avatar/getListaAvatares";
    }
}
