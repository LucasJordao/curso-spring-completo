package com.lucaswilliam.cursospringcompleto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaswilliam.cursospringcompleto.domains.Cliente;
import com.lucaswilliam.cursospringcompleto.repositories.ClienteRepository;
import com.lucaswilliam.cursospringcompleto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	
	/**
	 * Metodo responsavel por consultar um cliente no banco de dados por meio do id
	 * @param id
	 * @return - Objeto do tipo Cliente
	 */
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id: " 
				+ id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
