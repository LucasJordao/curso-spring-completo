package com.lucaswilliam.cursospringcompleto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaswilliam.cursospringcompleto.domains.Pedido;
import com.lucaswilliam.cursospringcompleto.repositories.PedidoRepository;
import com.lucaswilliam.cursospringcompleto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	
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
	
}
