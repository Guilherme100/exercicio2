package com.example.cadastroex;

import java.io.Serializable;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1l;

	private Long id;
	private String nome;
	private String telefone;
	private String foto;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}


	

}
