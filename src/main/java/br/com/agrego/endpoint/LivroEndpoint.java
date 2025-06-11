package br.com.agrego.endpoint;

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

import br.com.agrego.model.Livro;
import br.com.agrego.model.dto.LivroFiltro;
import br.com.agrego.model.dto.LivroResponse;
import br.com.agrego.service.LivroService;
import jakarta.annotation.security.RolesAllowed;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/livro")
@RolesAllowed("LIVRO")
public class LivroEndpoint {
	Logger LOGGER = LoggerFactory.getLogger(LivroEndpoint.class);
	
	@Autowired
	LivroService service;

	@RolesAllowed("LIVRO_PESQUISAR")
	@GetMapping
	public ResponseEntity<Page<LivroResponse>> findAll(@PageableDefault(size = 10) Pageable page, @ModelAttribute LivroFiltro filtro) {
		try {
			
			Page<LivroResponse> findAll = service.findByFiltro(page, filtro).map(LivroResponse::valueOf); ;

			if (findAll.isEmpty()) {
				return new ResponseEntity<>(findAll, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("LIVRO_PESQUISAR")
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getById(@PathVariable("id") long id) {
		Optional<Livro> livro = service.findById(id);

		if (livro.isPresent()) {
			return new ResponseEntity<>(livro.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RolesAllowed("LIVRO_CRIAR")
	@PostMapping
	public ResponseEntity<Livro> create(@RequestBody Livro livro) {
		try {
			Livro livroSalvo = service.save(livro);
			return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("ERRO", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("LIVRO_ALTERAR")
	@PutMapping("/{id}")
	public ResponseEntity<Livro> update(@PathVariable("id") long id, @RequestBody Livro livro) {
		Optional<Livro> oBean = service.findById(id);

		if (oBean.isPresent()) {
			Livro bean = oBean.get(); 
			BeanUtils.copyProperties(livro, bean, "id");
			return new ResponseEntity<>(service.save(bean), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RolesAllowed("LIVRO_DELETAR")
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

	@RolesAllowed("LIVRO_DELETAR")
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

}
