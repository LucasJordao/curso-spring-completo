package com.lucaswilliam.cursospringcompleto.domains.enums;

public enum EstadoPagamento {
	
	//Attributes
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int code;
	private String descricao;
	
	//Constructor
	private EstadoPagamento(int code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}
	
	//Getters
	public int getCode() {
		return this.code;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	//Methods
	public static EstadoPagamento toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(EstadoPagamento x: EstadoPagamento.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Codigo invalido. Codigo: " + code);
	}
	
}
