package com.jeanlima.springmvcdatajpaapp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jeanlima.springmvcdatajpaapp.model.Aluno;
import com.jeanlima.springmvcdatajpaapp.model.Curso;
import com.jeanlima.springmvcdatajpaapp.model.Disciplina;
import com.jeanlima.springmvcdatajpaapp.repository.AlunoRepository;
import com.jeanlima.springmvcdatajpaapp.repository.CursoRepository;
import com.jeanlima.springmvcdatajpaapp.repository.DisciplinaRepository;

@SpringBootApplication
public class SpringMvcDatajpaAppApplication {

	@Autowired
	CursoRepository cursoRepository;
	@Autowired
	AlunoRepository alunoRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

	@Bean
	public CommandLineRunner init(){
		return args -> {
            
            
            /*
             * Cadastrando cursos
             */

            cursoRepository.save(new Curso("BTI"));
            cursoRepository.save(new Curso("Engenharia de Software"));
            cursoRepository.save(new Curso("Ciência da Computação"));
            cursoRepository.save(new Curso("Engenharia de Computação"));

            System.out.println("Cursos cadastrados");
            List<Curso> cursos = cursoRepository.findAll();
            cursos.forEach(System.out::println);

            /**
             * Cadastrando disciplinas
             */



            Disciplina d1 = new Disciplina("Desenvolvimento de Sistemas Web I", "IMD0404", "Ementa 1");
            Disciplina d2 = new Disciplina("Fundamentos Matemáticos da Computação I", "IMD0028", "Ementa 2");
            Disciplina d3 = new Disciplina("Lógica aplicada à engenharia de software", "DIM0505", "Ementa 3");
            Disciplina d4 = new Disciplina("Compiladores", "DIM0164", "Ementa 4");

            d1.setCursos(cursos.subList(0, 2));
            d2.setCursos(cursos.subList(1, 2));
            d3.setCursos(cursos.subList(3, 4));
            d4.setCursos(cursos.subList(2, 4));


            disciplinaRepository.save(d1);
            disciplinaRepository.save(d2);
            disciplinaRepository.save(d3);
            disciplinaRepository.save(d4);

            System.out.println("Disciplinas cadastradas");
            List<Disciplina> disciplinas = disciplinaRepository.findAll();
            disciplinas.forEach(System.out::println);

            /*
             * Cadastrando alunos
             */

            Aluno aluno = new Aluno("Jose Silva");
            aluno.setCurso(cursos.get(0));
            Aluno aluno2 = new Aluno("João Maria");
            aluno2.setCurso(cursos.get(1));
            Aluno aluno3 = new Aluno("Maria Lima");
            aluno3.setCurso(cursos.get(2));

            alunoRepository.save(aluno);
            alunoRepository.save(aluno2);
            alunoRepository.save(aluno3);
 
                        
            //FETCH LAZY OR EAGER?
            List<Aluno> alunos = alunoRepository.findAll();
            System.out.println("LISTANDO ESTUDANTES E DISCIPLINAS - SEM FETCH");
           /* List<Aluno> alunos = alunoRepository.findAllFetchDisciplinas();
           System.out.println("LISTANDO ESTUDANTES E DISCIPLINAS - FETCH"); */
            alunos.forEach(
                e -> {
                    System.out.println(e.toString());
                    //System.out.println(e.getDisciplinas().toString());
                }
                
            );
            
            
//            List<Aluno> alunoPorDisciplina = alunoRepository.findAllByDisciplinaId(disciplinas.get(1).getId());
//            System.out.println("Lista Alunos por Disciplina");
//            alunoPorDisciplina.forEach(
//                e -> {
//                    System.out.println(e.toString());
//                    //System.out.println(e.getDisciplinas().toString());
//
//
//                }
//
//            );

            
//            Aluno alunoDisciplinas = alunoRepository.findById(2).map(e -> {return e;}).orElseThrow();
//            List<Disciplina> disciplinasPorAluno = disciplinaRepository.findAllByAlunoId(alunoDisciplinas.getId());
//            System.out.println("Aluno");
//            System.out.println(alunoDisciplinas.toString());
//            alunoDisciplinas.setDisciplinas(disciplinasPorAluno);
//            System.out.println("Disciplinas");
//            System.out.println(alunoDisciplinas.getDisciplinas().toString());

            


            

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcDatajpaAppApplication.class, args);
	}

}
