package com.example.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comentario {
	
	@Id
    @GeneratedValue
	private Integer id;
	@Column(columnDefinition="bigint")
	private Integer idautor;
	@Column(columnDefinition="bigint")
	private Integer idtopico;
	@Column(columnDefinition="text")
	private String texto;
	
	public Comentario() {
		
	}
	
	public Comentario(Integer id, Integer idautor, Integer idtopico, String texto) {
		super();
		this.id = id;
		this.idautor = idautor;
		this.idtopico = idtopico;
		this.texto = texto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdautor() {
		return idautor;
	}

	public void setIdautor(int idautor) {
		this.idautor = idautor;
	}

	public Integer getIdtopico() {
		return idtopico;
	}

	public void setIdtopico(int idtopico) {
		this.idtopico = idtopico;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "ComFilmes [id=" + id + ", idautor=" + idautor + ", idtopico=" + idtopico + ", texto=" + texto + "]";
	}
}

