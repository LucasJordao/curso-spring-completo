package com.lucaswilliam.cursospringcompleto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaswilliam.cursospringcompleto.domains.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
