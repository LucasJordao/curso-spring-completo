package com.lucaswilliam.cursospringcompleto.domains;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucaswilliam.cursospringcompleto.domains.pks.ItemPedidoPK;

@Entity
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	//Attributes
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	//Constructors and Overloads
	public ItemPedido() {
		
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double price) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = price;
	}

	//Getters and Setters
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
	}
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrice() {
		return preco;
	}

	public void setPrice(Double price) {
		this.preco = price;
	}

	//HashCode and Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
