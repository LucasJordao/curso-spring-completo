package com.lucaswilliam.cursospringcompleto.domains.enums;

public enum TipoCliente {
	//Enums Values 
	PESSOAJURIDICA(1, "Pessoa Jurídica"),
	PESSOAFISICA(2, "Pessoa Física");
	
	private int code;
	private String descricao;
	
	//Constructors and Overloads
	private TipoCliente(int code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	//Getters
	public int getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	//Methods
	public static TipoCliente toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for(TipoCliente x: TipoCliente.values()) {
			if(x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + code);
	}
}
