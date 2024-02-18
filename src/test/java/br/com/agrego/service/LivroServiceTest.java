package br.com.agrego.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.agrego.model.Livro;

@SpringBootTest
public class LivroServiceTest {

	@Autowired
	private LivroService livroService;
	
	@Test
	void deveTestarGravarNovoLivroVazio() {
		Livro entity = new Livro();
		
		Livro _entity = livroService.save(entity);
		
		assertNotNull(_entity.getId());
	}
}
