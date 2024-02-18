package br.com.agrego.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Livro;
import br.com.agrego.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	public Optional<Livro> findById(long id) {
		return livroRepository.findById(id);
	}

	public Livro save(Livro livro) {
		return livroRepository.save(livro);
	}

	public void deleteById(long id) {
		livroRepository.deleteById(id);
	}

	public void deleteAll() {
		livroRepository.deleteAll();
	}

	public List<Livro> findByTitulo(String titulo) {
		return livroRepository.findByTituloContaining(titulo);
	}
	
}
