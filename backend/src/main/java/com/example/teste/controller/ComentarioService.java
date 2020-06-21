package com.example.teste.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.teste.model.Comentario;
import com.example.teste.repository.ComentarioRepository;

 
@CrossOrigin
@RestController
@RequestMapping(path = "/api/coments")
public class ComentarioService {
 
    @Autowired
    private ComentarioRepository comts;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> getComentarios() {
    	System.out.println("oi bom");
        return new ResponseEntity<List<Comentario>>(comts.findAll(), HttpStatus.OK);
        //return new ResponseEntity<List<Curso>>(cursos.findAll(new Sort(Sort.Direction.ASC, "id")), HttpStatus.OK);
    }
    
    /*
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Comentario> getComent(@PathVariable("id") Integer id) {
        Optional<Comentario> comt = comts.findById(id);
 
        if (comt.isPresent()) {
            return new ResponseEntity<Comentario>(comt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
    
   
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Comentario> getComentario(@RequestParam("texto") String texto) {
        System.out.println(texto);
        Comentario post = comts.findByNome(texto);
 
        if (post != null) {
            return new ResponseEntity<Comentario>(post, HttpStatus.OK);
        } else {
        		System.out.println("error!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comentario> addComentario(@RequestBody Integer idautor, Integer idtopico, String texto) {
    	
    	
        if (texto == null || texto.equals("null")) {
        	System.out.println("ComentarioService - Não preencheu tds os campos!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        
        Comentario comt = new Comentario(null, idautor, idtopico, texto);
        
        Comentario cursoAux = comts.save(comt);
        /*
        try {
            Upload.uploadFile(image.getInputStream(), cursoAux.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
 
        return new ResponseEntity<Comentario>(cursoAux, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable("id") int id, String texto, MultipartFile image) {
    	
    	if (texto == null || texto.equals("null") ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Optional<Comentario> comt = comts.findById(id);
 
        if (comt.isPresent()) {
        	comt.get().setTexto(texto);
        	
        	comts.save(comt.get());
        	/*
            try {
                if (image != null) {
                    Upload.uploadFile(image.getInputStream(), id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
 
            return new ResponseEntity<Comentario>(comt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarComentario(@PathVariable("id") int id) {
        if(comts.existsById(id)) {
        	comts.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

