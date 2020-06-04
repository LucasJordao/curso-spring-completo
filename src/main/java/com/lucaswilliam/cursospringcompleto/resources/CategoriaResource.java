package com.lucaswilliam.cursospringcompleto.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;
import com.lucaswilliam.cursospringcompleto.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	//Service da categoria
	@Autowired
	private CategoriaService service;
	
	@GetMapping()
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> lista = service.findAll();
		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id){
		Categoria obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
