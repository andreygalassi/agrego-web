package br.com.agrego.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.enuns.EnumRecurso;
import br.com.agrego.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	PerfilRepository perfilRepository;

	public List<Perfil> findAll() {
		return perfilRepository.findAll();
	}

	public Optional<Perfil> findById(long id) {
		return perfilRepository.findById(id);
	}

	public Perfil save(Perfil perfil) {
		return perfilRepository.save(perfil);
	}

	public void deleteById(long id) {
		perfilRepository.deleteById(id);
	}

	public void deleteAll() {
		perfilRepository.deleteAll();
	}

	public Optional<Perfil> findByRole(EnumRecurso role) {
		return null;//perfilRepository.findByRole(role);
	}

}
