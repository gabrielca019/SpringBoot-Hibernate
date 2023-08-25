package com.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Usuario;
import com.springboot.repositories.IUsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}
	
	public Usuario insert(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
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