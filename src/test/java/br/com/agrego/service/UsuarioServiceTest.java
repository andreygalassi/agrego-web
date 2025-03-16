package br.com.agrego.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.Usuario;

@SpringBootTest
public class UsuarioServiceTest {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PerfilService perfilService;
	
	@Test
	void deveTestarCriarUsuario() {
		Perfil perfil = new Perfil();
		perfil.setNome("AUTOR");
		perfil = perfilService.save(perfil);
		
		Usuario entity = new Usuario();
		
		entity.setLogin("usuario1");
		entity.setSenha("senha1");
		entity.setPerfis(new HashSet<>());
		entity.getPerfis().add(perfil);
		
		Usuario _entity = usuarioService.save(entity);
		
		assertNotNull(_entity.getId());
	}
}
