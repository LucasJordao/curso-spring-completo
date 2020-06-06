package com.lucaswilliam.cursospringcompleto.dto;

import java.io.Serializable;

import com.lucaswilliam.cursospringcompleto.domains.Produto;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private Integer id;
	private String nome;
	private Double preco;
	
	//Constructors and overloads
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Integer id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
