package com.example.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.teste.model.Postagem;


public interface PostagemRepository extends JpaRepository<Postagem, Integer> {
	@Query("from Postagem where topico = ?1")
	Postagem findByNome(String topico);
	
	List<Postagem> findByTopicoContaining(String topico);
}
