package com.lucaswilliam.cursospringcompleto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaswilliam.cursospringcompleto.domains.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
