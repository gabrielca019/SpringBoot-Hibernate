package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entities.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long>{

}