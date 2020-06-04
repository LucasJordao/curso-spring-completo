package com.lucaswilliam.cursospringcompleto.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;
import com.lucaswilliam.cursospringcompleto.repositories.CategoriaRepository;

@Configuration
public class Instantiate implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Instanciação das categorias
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
