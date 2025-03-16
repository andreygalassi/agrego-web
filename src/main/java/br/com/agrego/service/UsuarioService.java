package br.com.agrego.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Autor;
import br.com.agrego.model.Usuario;
import br.com.agrego.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> findById(long id) {
		return usuarioRepository.findById(id);
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void deleteById(long id) {
		usuarioRepository.deleteById(id);
	}

	public void deleteAll() {
		usuarioRepository.deleteAll();
	}

	public Optional<Usuario> findByNome(String login) {
		return usuarioRepository.findByLogin(login);
	}
	
}
