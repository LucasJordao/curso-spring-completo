package com.lucaswilliam.cursospringcompleto.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.lucaswilliam.cursospringcompleto.domains.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	private Date dataVencimento;
	private Date dataPagamento;
	
	//Constructors and Overloads
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Date dataVencimento, Date dataPagamento, Pedido pedido) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	//Getters and Setters
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
