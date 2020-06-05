package com.lucaswilliam.cursospringcompleto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;
import com.lucaswilliam.cursospringcompleto.repositories.CategoriaRepository;
import com.lucaswilliam.cursospringcompleto.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	/**
	 * Metodo responsável por fazer a consulta de categorias no banco de dados
	 * @return - Lista do tipo Categoria
	 */
	public List<Categoria> findAll(){
		List<Categoria> lista = repo.findAll();
		
		return lista;
	}
	
	/**
	 * Metodo responsavel por consultar uma categoria no banco de dados por meio do id
	 * @param id
	 * @return - Objeto do tipo Categoria
	 */
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", Tipo: " + Categoria.class.getName()));
	}
	
	/**
	 * Metodo responsavel por inserir uma categoria na base de dados
	 * @param obj
	 * @return - Objeto do tipo Categoria
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	/**
	 * Metodo responsavel por atualizar uma categoria na base de dados
	 * @param obj
	 * @return - Objeto do tipo Categoria
	 */
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
}
