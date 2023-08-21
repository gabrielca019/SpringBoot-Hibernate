package com.springboot.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entities.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@GetMapping
	public ResponseEntity<Usuario> buscarTodos() {
		Usuario u = new Usuario(1L, "Gabriel", "gabriel@gmail.com", "19970707070", "123");
		return ResponseEntity.ok().body(u);
	}

}