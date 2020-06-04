package com.lucaswilliam.cursospringcompleto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaswilliam.cursospringcompleto.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
