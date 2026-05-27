package br.cefetmg.grow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.cefetmg.grow.model.Professor;
import br.cefetmg.grow.repository.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/professores")

public class ProfessorController {
    private ProfessorRepository repository;
    public ProfessorController(ProfessorRepository repository){
        this.repository = repository;
    }
    private static List<Professor> ProfessorList;
    private int nextId = 1;
    {
        ProfessorList = new ArrayList<>();
        Professor ent1 = new Professor();
        ent1.setId((long) nextId++);
        ent1.setNome("Luciano");
        ent1.setSenha("123456");
        Professor ent2 = new Professor();
        ent2.setId((long) nextId++);
        ent2.setNome("Balbino");
        ent2.setSenha("654321");
        ProfessorList.add(ent1);
        ProfessorList.add(ent2);
    }

    @GetMapping("")
    public List<Professor> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Professor getById(@PathVariable long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("")
    public Professor inserir(@RequestBody Professor professor) {
        professor.setId(null);
        repository.save(professor);
        return professor;
    }

    @DeleteMapping("/{id}")
    public Professor excluirProfessor(@PathVariable long id) {
       Professor professor = repository.findById(id).orElse(null);
       if(professor == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado");
       }
       repository.delete(professor);
       return professor;
    }


    @PutMapping("")
    public Professor alterarProfessor(@RequestBody Professor professor) {
      if(professor.getId() == null){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");
      }
      repository.save(professor);
      return professor;

  }
}
