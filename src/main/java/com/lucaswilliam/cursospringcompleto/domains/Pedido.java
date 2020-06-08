package com.lucaswilliam.cursospringcompleto.domains;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT")
	private Date instante;
	
	//Assosciations
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	//Constructors and Overloads
	public Pedido() {
		
	}

	public Pedido(Integer id, Date instante, Endereco enderecoDeEntrega, Cliente cliente) {
		super();
		this.id = id;
		this.instante = instante;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.cliente = cliente;
	}

	//Getters and Setters
	public double getValorTotal() {
		double sum = 0.0;
		
		for(ItemPedido ip: itens) {
			sum += ip.getSubTotal();
		}
		
		return sum;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(id);
		builder.append(", Instante: ");
		builder.append(sdf.format(this.getInstante()));
		builder.append(", Clinte: ");
		builder.append(this.getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(this.getPagamento().getEstado().getDescricao());
		builder.append("\n Detalhes:\n");
		for(ItemPedido ip: this.getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor Total: ");
		builder.append(nf.format(this.getValorTotal()));
		return builder.toString();
	}
	
	
	
}
