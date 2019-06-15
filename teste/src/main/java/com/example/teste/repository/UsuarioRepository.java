package com.example.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.teste.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("from Usuario where nome = ?1")
	Usuario findByNome(String nome);
	
	@Query("from Usuario where username = ?1 and password = ?2")
	Usuario findUsernamePassword(String username, String password);
}
