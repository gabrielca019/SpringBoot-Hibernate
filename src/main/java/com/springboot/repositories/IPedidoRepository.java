package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entities.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long>{

}