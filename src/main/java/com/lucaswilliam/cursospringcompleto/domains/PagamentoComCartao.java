package com.lucaswilliam.cursospringcompleto.domains;


import javax.persistence.Entity;

import com.lucaswilliam.cursospringcompleto.domains.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private Integer numeroDeParcelas;
	
	//Constructors and Overloads
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Integer numeroDeParcelas, Pedido pedido) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	//Getters and Setters
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
