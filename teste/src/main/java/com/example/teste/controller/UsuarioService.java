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

import com.example.teste.model.Usuario;
import com.example.teste.repository.UsuarioRepository;
import com.example.teste.util.Upload;
 
@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin
public class UsuarioService {
 
    @Autowired
    private UsuarioRepository users;
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> getUsers() {
        return new ResponseEntity<List<Usuario>>(users.findAll(), HttpStatus.OK);
        //return new ResponseEntity<List<Curso>>(cursos.findAll(new Sort(Sort.Direction.ASC, "id")), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUser(@PathVariable("id") Integer id) {
        Optional<Usuario> user = users.findById(id);
 
        if (user.isPresent()) {
            return new ResponseEntity<Usuario>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Usuario> getUserPass(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        Usuario user = users.findUsernamePassword(username, password);
 
        if (user != null) {
            return new ResponseEntity<Usuario>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUser(@RequestParam("nome") String nome) {
        System.out.println(nome);
        Usuario user = users.findByNome(nome);
 
        if (user != null) {
            return new ResponseEntity<Usuario>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Usuario> addUser(String username, String password, String email, String nomecompleto, String  endereco, String  cidade, String  estado, String pais, MultipartFile image) {
        if (username == null || password == null || username.equals("null") || password.equals("null")) {
        	System.out.println("oi RUIM");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Oi usuario");
        Usuario curso = new Usuario(null, username, password, email, nomecompleto, endereco, cidade, estado, pais);
        
        Usuario cursoAux = users.save(curso);
        /*
        try {
            Upload.uploadFile(image.getInputStream(), cursoAux.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
 
        return new ResponseEntity<Usuario>(cursoAux, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> atualizarUser(@PathVariable("id") int id, String username, String password, String email, String nomecompleto, String  endereco, String  cidade, String  estado, String pais,
            MultipartFile image) {
        if (username == null || password == null || username.equals("null") || password.equals("null")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Usuario> user = users.findById(id);
 
        if (user.isPresent()) {
        	user.get().setUsername(username);
        	user.get().setPassword(password);
        	user.get().setEmail(email);
        	user.get().setNomecompleto(nomecompleto);
        	user.get().setEndereco(endereco);
        	user.get().setCidade(cidade);
        	user.get().setEstado(estado);
        	user.get().setPais(pais);
            
            users.save(user.get());
            try {
                if (image != null) {
                    Upload.uploadFile(image.getInputStream(), id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            return new ResponseEntity<Usuario>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarUser(@PathVariable("id") int id) {
        if(users.existsById(id)) {
        	users.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

