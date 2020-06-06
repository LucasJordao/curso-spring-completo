package com.lucaswilliam.cursospringcompleto.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucaswilliam.cursospringcompleto.domains.Produto;
import com.lucaswilliam.cursospringcompleto.dto.ProdutoDTO;
import com.lucaswilliam.cursospringcompleto.resources.utils.URL;
import com.lucaswilliam.cursospringcompleto.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	//Service
	@Autowired
	private ProdutoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id){
		Produto obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name = "page", defaultValue ="0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue ="24") Integer linesPerPage,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "nome", defaultValue="") String nome,
			@RequestParam(name = "categorias", defaultValue = "") String categorias){
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
	
}
