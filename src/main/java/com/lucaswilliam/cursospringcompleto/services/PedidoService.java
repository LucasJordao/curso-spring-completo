package com.lucaswilliam.cursospringcompleto.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucaswilliam.cursospringcompleto.domains.ItemPedido;
import com.lucaswilliam.cursospringcompleto.domains.PagamentoComBoleto;
import com.lucaswilliam.cursospringcompleto.domains.Pedido;
import com.lucaswilliam.cursospringcompleto.domains.enums.EstadoPagamento;
import com.lucaswilliam.cursospringcompleto.repositories.ItemPedidoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.PagamentoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.PedidoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.ProdutoRepository;
import com.lucaswilliam.cursospringcompleto.resources.BoletoService;
import com.lucaswilliam.cursospringcompleto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;
	
	/**
	 * Metodo responsavel por consultar um pedido no banco de dados por meio do id
	 * @param id
	 * @return - Objeto do tipo Pedido
	 */
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id: " 
				+ id + ", Tipo: " + Pedido.class.getName()));
	}
	
	/**
	 * Metodo responsavel por inserir um pedido no banco de dados
	 * @param obj
	 * @return - Objeto do tipo Pedido
	 */
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPrice(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
	
}
