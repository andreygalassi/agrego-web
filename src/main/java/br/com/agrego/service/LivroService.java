package br.com.agrego.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Livro;
import br.com.agrego.model.dto.LivroFiltro;
import br.com.agrego.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository repo;

	public Page<Livro> findByFiltro(Pageable page, LivroFiltro filtro) {
		return repo.findByFiltro(page, filtro);
	}
	
	public List<Livro> findAll() {
		return repo.findAll();
	}

	public Optional<Livro> findById(long id) {
		return repo.findById(id);
	}

	public Livro save(Livro livro) {
		return repo.save(livro);
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

}
