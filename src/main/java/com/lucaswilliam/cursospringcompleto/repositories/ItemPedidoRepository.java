package com.lucaswilliam.cursospringcompleto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaswilliam.cursospringcompleto.domains.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
