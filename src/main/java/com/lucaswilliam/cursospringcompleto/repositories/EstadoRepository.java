package com.lucaswilliam.cursospringcompleto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaswilliam.cursospringcompleto.domains.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
