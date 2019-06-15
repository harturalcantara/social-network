package com.example.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	 @Column(columnDefinition="text")
	private String username;
	 @Column(columnDefinition="text")
	private String password;
	 @Column(columnDefinition="text")
	private String email;
	 @Column(columnDefinition="text")
	private String nomecompleto;
	 @Column(columnDefinition="text")
	private String endereco;
	 @Column(columnDefinition="text")
	private String cidade;
	 @Column(columnDefinition="text")
	private String estado;
	 @Column(columnDefinition="text")
	private String pais;
	
	 
	public Usuario() {
		
	}
	
	public Usuario(Integer id, String username, String password, String email, String nomecompleto, String endereco,
			String cidade, String estado, String pais) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nomecompleto = nomecompleto;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", nomecompleto=" + nomecompleto + ", endereco=" + endereco + ", cidade=" + cidade + ", estado="
				+ estado + ", pais=" + pais + "]";
	}


	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomecompleto() {
		return nomecompleto;
	}


	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}
		


}
