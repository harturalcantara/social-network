package com.example.teste.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.teste.model.Curso;
import com.example.teste.repository.CursoRepository;
import com.example.teste.util.Upload;
 
@RestController
@RequestMapping(path = "/api/cursos")
@CrossOrigin
public class CursoService {
 
    @Autowired
    private CursoRepository cursos;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Curso>> getCursos() {
        return new ResponseEntity<List<Curso>>(cursos.findAll(), HttpStatus.OK);
        //return new ResponseEntity<List<Curso>>(cursos.findAll(new Sort(Sort.Direction.ASC, "id")), HttpStatus.OK);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Curso> getCurso(@PathVariable("id") Integer id) {
        Optional<Curso> curso = cursos.findById(id);
 
        if (curso.isPresent()) {
            return new ResponseEntity<Curso>(curso.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
   
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Curso> getCurso(@RequestParam("nome") String nome) {
        System.out.println(nome);
        Curso curso = cursos.findByNome(nome);
 
        if (curso != null) {
            return new ResponseEntity<Curso>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Curso> addCurso(String nome, String duracao, MultipartFile image) {
        if (nome == null || duracao == null || nome.equals("null") || duracao.equals("null") || image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Curso curso = new Curso(null, nome, duracao);
        Curso cursoAux = cursos.save(curso);
        try {
            Upload.uploadFile(image.getInputStream(), cursoAux.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new ResponseEntity<Curso>(curso, HttpStatus.OK);
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Curso> atualizarCurso(@PathVariable("id") int id, String nome, String duracao,
            MultipartFile image) {
        if (nome == null || duracao == null || nome.equals("null") || duracao.equals("null")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Curso> curso = cursos.findById(id);
 
        if (curso.isPresent()) {
            curso.get().setNome(nome);
            curso.get().setDuracao(duracao);
            cursos.save(curso.get());
            try {
                if (image != null) {
                    Upload.uploadFile(image.getInputStream(), id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            return new ResponseEntity<Curso>(curso.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarCurso(@PathVariable("id") int id) {
        if(cursos.existsById(id)) {
            cursos.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
