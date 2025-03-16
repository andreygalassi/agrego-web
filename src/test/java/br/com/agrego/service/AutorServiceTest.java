package br.com.agrego.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.agrego.model.Autor;

@SpringBootTest
public class AutorServiceTest {

	@Autowired
	private AutorService autorService;
	
	@Test
	void deveTestarGravarNovoLivroVazio() {
		Autor entity = new Autor();
		
		Autor _entity = autorService.save(entity);
		
		assertNotNull(_entity.getId());
	}
}
