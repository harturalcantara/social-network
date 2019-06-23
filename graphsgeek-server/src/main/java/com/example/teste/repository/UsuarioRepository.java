package com.example.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.teste.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("from Usuario where username = ?1")
	Usuario findByUsername(String nome);
	
	@Query("from Usuario where username = ?1 and password = ?2")
	Usuario findUsernamePassword(String username, String password);
	
	List<Usuario> findByUsernameContaining(String username);
	
}
