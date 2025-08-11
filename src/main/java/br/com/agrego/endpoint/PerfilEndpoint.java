package br.com.agrego.endpoint;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.dto.PerfilFiltro;
import br.com.agrego.model.enuns.EnumAcao;
import br.com.agrego.model.enuns.EnumRecurso;
import br.com.agrego.service.PerfilService;
import jakarta.annotation.security.RolesAllowed;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/perfil")
@RolesAllowed("PERFIL")
public class PerfilEndpoint {
	Logger LOGGER = LoggerFactory.getLogger(PerfilEndpoint.class);
	
	@Autowired
	PerfilService service;

	@RolesAllowed("PERFIL_PESQUISAR")
	@GetMapping
	public ResponseEntity<Page<Perfil>> findAll(@PageableDefault(size = 10) Pageable page, @ModelAttribute PerfilFiltro filtro) {
		try {
			
			Page<Perfil> findAll = service.findByFiltro(page, filtro);

			if (findAll.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("PERFIL_PESQUISAR")
	@GetMapping("/{id}")
	public ResponseEntity<Perfil> getById(@PathVariable("id") long id) {
		Optional<Perfil> autor = service.findById(id);

		if (autor.isPresent()) {
			return new ResponseEntity<>(autor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RolesAllowed("PERFIL_CRIAR")
	@PostMapping
	public ResponseEntity<Perfil> create(@RequestBody Perfil item) {
		try {
			Perfil itemSalvo = service.save(item);
			return new ResponseEntity<>(itemSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("PERFIL_ALTERAR")
	@PutMapping("/{id}")
	public ResponseEntity<Perfil> update(@PathVariable("id") long id, @RequestBody Perfil item) {
		Optional<Perfil> oBean = service.findById(id);

		if (oBean.isPresent()) {
			Perfil bean = oBean.get(); 
			BeanUtils.copyProperties(item, bean, "id");
			return new ResponseEntity<>(service.save(bean), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RolesAllowed("PERFIL_DELETAR")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("PERFIL_DELETAR")
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			service.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RolesAllowed("PERFIL_PESQUISAR")
	@GetMapping("/listaRecurso")
	public ResponseEntity<List<EnumRecurso>> listaRecurso() {
		try {
			
			List<EnumRecurso> lista = service.listaRecurso();

			if (lista.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RolesAllowed("PERFIL_PESQUISAR")
	@GetMapping("/listaAcao")
	public ResponseEntity<List<EnumAcao>> listaAcao() {
		try {
			
			List<EnumAcao> lista = service.listaAcao();
					
					if (lista.isEmpty()) {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
			
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
