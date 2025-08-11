package br.com.agrego.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.dto.PerfilFiltro;
import br.com.agrego.model.enuns.EnumAcao;
import br.com.agrego.model.enuns.EnumRecurso;
import br.com.agrego.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	PerfilRepository repo;


	public Page<Perfil> findByFiltro(Pageable page, PerfilFiltro filtro) {
		return repo.findByFiltro(page, filtro);
	}
	
	public List<Perfil> findAll() {
		return repo.findAll();
	}

	public Optional<Perfil> findById(long id) {
		return repo.findById(id);
	}

	public Perfil save(Perfil perfil) {
		return repo.save(perfil);
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

	public Optional<Perfil> findByRole(EnumRecurso role) {
		return null;//perfilRepository.findByRole(role);
	}

	public List<EnumRecurso> listaRecurso() {
		return Arrays.asList(EnumRecurso.values());
	}
	
	public List<EnumAcao> listaAcao() {
		return Arrays.asList(EnumAcao.values());
	}

}
