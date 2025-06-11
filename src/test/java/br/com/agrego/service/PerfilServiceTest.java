package br.com.agrego.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.Usuario;
import br.com.agrego.model.enuns.EnumAcao;
import br.com.agrego.model.enuns.EnumRecurso;
import jakarta.transaction.Transactional;

@SpringBootTest
public class PerfilServiceTest {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PerfilService perfilService;
	
	@Test
	@Transactional
	void deveListarRolesDosPerfis() {
		List<Perfil> listaPerfil = perfilService.findAll();
		System.out.println(listaPerfil);
		listaPerfil.forEach(x -> System.out.println(x.getListaRolesName()));
	}
	
}
