package com.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springboot.entities.Usuario;
import com.springboot.repositories.IUsuarioRepository;
import com.springboot.services.exceptions.DatabaseException;
import com.springboot.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario insert(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deleteById(Long id) {
		try {
			if(!usuarioRepository.existsById(id)) 
				throw new ResourceNotFoundException(id);
			usuarioRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Usuario updateById(Long id, Usuario usuario) {
		Usuario entidade = usuarioRepository.getReferenceById(id);
		updateData(entidade, usuario);
		return usuarioRepository.save(entidade);
	}
	
	public void updateData(Usuario entidade, Usuario usuario) {
		entidade.setNome(usuario.getNome());
		entidade.setEmail(usuario.getEmail());
		entidade.setTelefone(usuario.getTelefone());
	}

}