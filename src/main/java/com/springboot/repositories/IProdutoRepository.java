package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entities.Produto;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long>{

}