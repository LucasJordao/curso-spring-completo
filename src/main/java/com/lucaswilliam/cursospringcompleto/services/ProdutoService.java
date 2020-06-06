package com.lucaswilliam.cursospringcompleto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;
import com.lucaswilliam.cursospringcompleto.domains.Produto;
import com.lucaswilliam.cursospringcompleto.repositories.CategoriaRepository;
import com.lucaswilliam.cursospringcompleto.repositories.ProdutoRepository;
import com.lucaswilliam.cursospringcompleto.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	/**
	 * Metodo responsavel por consultar um produto no banco de dados por meio do id
	 * @param id
	 * @return - Objeto do tipo Produto
	 */
	public Produto findById(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id: " 
				+ id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return repo.search(nome, categorias, pageRequest);
	}
	
}
