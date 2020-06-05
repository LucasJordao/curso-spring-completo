package com.lucaswilliam.cursospringcompleto.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;


public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max =80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	//Constructors and Overloads
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
