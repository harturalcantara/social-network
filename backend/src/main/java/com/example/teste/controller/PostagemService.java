package com.example.teste.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.teste.model.Postagem;
import com.example.teste.repository.PostagemRepository;
import com.example.teste.util.Upload;
 
@RestController
@RequestMapping(path = "/api/posts")
@CrossOrigin
public class PostagemService {
 
    @Autowired
    private PostagemRepository posts;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Postagem>> getPostagens() {
        //return new ResponseEntity<List<Postagem>>(posts.findAll(), HttpStatus.OK);
        return new ResponseEntity<List<Postagem>>(posts.findAll(new Sort(Sort.Direction.DESC, "id")), HttpStatus.OK); //ASC crescente
    }
    
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Postagem> getPostagem(@PathVariable("id") Integer id) {
        Optional<Postagem> post = posts.findById(id);
 
        if (post.isPresent()) {
            return new ResponseEntity<Postagem>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
    
    @RequestMapping(value = "/prefix", method = RequestMethod.GET)
    public ResponseEntity<List<Postagem>> getPostagemPrefix(String topico) {
        System.out.println(topico);
        System.out.println("estou aquiii!");
        List<Postagem> user = posts.findByTopicoContaining(topico);
        
        System.out.println(user.toString());
        if (user.size() > 0 ) {
            return new ResponseEntity<List<Postagem>>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
   
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Postagem> getPostagem(@RequestParam("topico") String topico) {
        System.out.println(topico);
        Postagem post = posts.findByNome(topico);
 
        if (post != null) {
            return new ResponseEntity<Postagem>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Postagem> addPostagem(Integer idautor, Integer idcategoria, String topico, String descricao, MultipartFile image) {
    	System.out.println("oi");
        if (topico == null || descricao == null || topico.equals("null") || descricao.equals("null") || image == null) {
        	System.out.println("PostagemService - Não preencheu tds os campos!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("oi bom");
        Postagem post = new Postagem(null, idautor, idcategoria, topico, descricao);
        
        Postagem cursoAux = posts.save(post);
        
        try {
            Upload.uploadFile(image.getInputStream(), cursoAux.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new ResponseEntity<Postagem>(cursoAux, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Postagem> atualizarPostagem(@PathVariable("id") int id, Integer idautor, Integer idcategoria, String topico, String descricao,MultipartFile image) {
        if (topico == null || topico == null || descricao.equals("null") || descricao.equals("null")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Postagem> post = posts.findById(id);
 
        if (post.isPresent()) {
        	post.get().setTopico(topico);
        	post.get().setDescricao(descricao);
            posts.save(post.get());
            try {
                if (image != null) {
                    Upload.uploadFile(image.getInputStream(), id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            return new ResponseEntity<Postagem>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarPostagem(@PathVariable("id") int id) {
    	
        if(posts.existsById(id)) {
        	posts.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

