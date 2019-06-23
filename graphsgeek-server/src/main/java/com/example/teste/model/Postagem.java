package com.example.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Postagem {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(columnDefinition="bigint")
	private Integer idautor;
	@Column(columnDefinition="bigint")
	private Integer idcategoria;
	@Column(columnDefinition="text")
	private String topico;
	@Column(columnDefinition="text")
	private String descricao;
	
	
	public Postagem() {
		
	}
	
	public Postagem(Integer id, Integer idautor, Integer idcategoria, String topico, String descricao) {
		super();
		this.id = id;
		this.idautor = idautor;
		this.idcategoria = idcategoria;
		this.topico = topico;
		this.descricao = descricao;
	}
		
	@Override
	public String toString() {
		return "Postagem [id=" + id + ", idautor=" + idautor + ", idcategoria=" + idcategoria + ", topico=" + topico
				+ ", descricao=" + descricao + "]";
	}

	public Integer getIdAutor() {
		return idautor;
	}

	public void setIdAutor(int idautor) {
		this.idautor = idautor;
	}

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	

}
