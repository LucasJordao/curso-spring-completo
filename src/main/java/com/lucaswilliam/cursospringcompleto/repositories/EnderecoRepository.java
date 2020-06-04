package com.lucaswilliam.cursospringcompleto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaswilliam.cursospringcompleto.domains.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
