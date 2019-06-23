package com.example.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.example.teste.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
	//@Query("from Comentario where topico = ?1")
	//Postagem findByNome(String topico);
	@Query("from Comentario where texto = ?1")
	Comentario findByNome(String topico);
}
