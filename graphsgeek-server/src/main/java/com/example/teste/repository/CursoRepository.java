package com.example.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.teste.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
	@Query("from Curso where nome = ?1")
	Curso findByNome(String nome);
}
