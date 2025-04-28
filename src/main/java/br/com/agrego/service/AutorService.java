package br.com.agrego.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Autor;
import br.com.agrego.model.dto.AutorFiltro;
import br.com.agrego.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorRepository autorRepository;

	public Page<Autor> findByFiltro(Pageable page, AutorFiltro filtro) {
		return autorRepository.findByFiltro(page, filtro);
	}
	
	public Page<Autor> findAll(Pageable page) {
		return autorRepository.findAll(page);
	}

	public Optional<Autor> findById(long id) {
		return autorRepository.findById(id);
	}

	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}

	public void deleteById(long id) {
		autorRepository.deleteById(id);
	}

	public void deleteAll() {
		autorRepository.deleteAll();
	}
}
