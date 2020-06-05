package com.lucaswilliam.cursospringcompleto.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucaswilliam.cursospringcompleto.domains.Cidade;
import com.lucaswilliam.cursospringcompleto.domains.Cliente;
import com.lucaswilliam.cursospringcompleto.domains.Endereco;
import com.lucaswilliam.cursospringcompleto.domains.enums.TipoCliente;
import com.lucaswilliam.cursospringcompleto.dto.ClienteDTO;
import com.lucaswilliam.cursospringcompleto.dto.ClienteNewDTO;
import com.lucaswilliam.cursospringcompleto.repositories.ClienteRepository;
import com.lucaswilliam.cursospringcompleto.repositories.EnderecoRepository;
import com.lucaswilliam.cursospringcompleto.services.exceptions.DataIntegrityException;
import com.lucaswilliam.cursospringcompleto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	/**
	 * Metodo responsavel por consultar um cliente no banco de dados por meio do id
	 * 
	 * @param id
	 * @return - Objeto do tipo Cliente
	 */
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	/**
	 * Metodo responsavel por inserir um cliente no banco de dados
	 * 
	 * @param obj
	 * @return - Objeto do tipo Cliente
	 */
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	/**
	 * Metodo responsavel por retornar uma lista de clientes
	 * @return - Lista de Clientes
	 */
	public List<Cliente> findAll() {
		List<Cliente> list = repo.findAll();

		return list;
	}

	/**
	 * Metodo responsavel por atualizar um cliente
	 * @param obj
	 * @return - void
	 */
	public void update(Cliente obj) {
		Cliente newObj = this.findById(obj.getId());
		updateData(newObj, obj);
		repo.save(newObj);
	}

	/**
	 * Metodo responsavel por deletar um cliente
	 * @param id
	 * @return - void
	 */
	public void delete(Integer id) {
		this.findById(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não pode deletar cliente");
		}
	}
	
	/**
	 * Metodo responsavel por capturar paginas de um cliente
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @return - page do tipo Cliente
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO obj) {
		Cliente cli = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), TipoCliente.toEnum(obj.getTipo()));
		Cidade cid = new Cidade(obj.getCidadeId(), null, null);
		Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(obj.getTelefone1());
		if(obj.getTelefone2() != null) {
			cli.getTelefones().add(obj.getTelefone2());
		}
		if(obj.getTelefone3() != null) {
			cli.getTelefones().add(obj.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		if(obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if(obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
	}
	
}
