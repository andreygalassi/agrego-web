package br.com.agrego.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Autor;
import br.com.agrego.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorRepository autorRepository;

	public List<Autor> findAll() {
		return autorRepository.findAll();
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

	public List<Autor> findByNome(String nome) {
		return autorRepository.findByNomeContaining(nome);
	}
	
}
