package com.lucaswilliam.cursospringcompleto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;
import com.lucaswilliam.cursospringcompleto.dto.CategoriaDTO;
import com.lucaswilliam.cursospringcompleto.repositories.CategoriaRepository;
import com.lucaswilliam.cursospringcompleto.services.exceptions.DataIntegrityException;
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
		List<Categoria> list = repo.findAll();
		
		return list;
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
	
	/**
	 * Metodo responsavel por deletar uma categoria na base de dados
	 * @param id
	 * @return - void
	 */
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	/**
	 * Metodo responsavel por capturar paginas de categorias na base de dados
	 * @param Page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return - Objeto do tipo Page<Categoria>
	 */
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
}
