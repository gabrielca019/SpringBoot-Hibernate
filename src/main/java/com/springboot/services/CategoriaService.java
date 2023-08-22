package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Categoria;
import com.springboot.repositories.ICategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).get();
	}

}