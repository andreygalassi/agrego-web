package br.com.agrego.endpoint;

import java.util.Optional;

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

import br.com.agrego.model.Autor;
import br.com.agrego.service.AutorService;
import jakarta.annotation.security.RolesAllowed;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/autor")
@RolesAllowed("AUTOR")
public class AutorEndpoint {
	
	@Autowired
	AutorService autorService;

	@RolesAllowed("AUTOR_PESQUISAR")
	@GetMapping
	public ResponseEntity<Page<Autor>> findAll(@PageableDefault(size = 10) Pageable page, @ModelAttribute Autor filtro) {
		try {
			
			Page<Autor> findAll = autorService.findByFiltro(page, filtro);

			if (findAll.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("AUTOR_PESQUISAR")
	@GetMapping("/{id}")
	public ResponseEntity<Autor> getById(@PathVariable("id") long id) {
		Optional<Autor> autor = autorService.findById(id);

		if (autor.isPresent()) {
			return new ResponseEntity<>(autor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RolesAllowed("AUTOR_CRIAR")
	@PostMapping
	public ResponseEntity<Autor> create(@RequestBody Autor autor) {
		try {
			Autor autorSalvo = autorService.save(autor);
			return new ResponseEntity<>(autorSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("AUTOR_ALTERAR")
	@PutMapping("/{id}")
	public ResponseEntity<Autor> update(@PathVariable("id") long id, @RequestBody Autor autor) {
		Optional<Autor> oBean = autorService.findById(id);

		if (oBean.isPresent()) {
			Autor bean = oBean.get(); //TODO copiar dados para bean
			return new ResponseEntity<>(autorService.save(bean), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RolesAllowed("AUTOR_DELETAR")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			autorService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RolesAllowed("AUTOR_DELETAR")
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			autorService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	@GetMapping("/autor")
//	public ResponseEntity<List<Autor>> findByTitulo(@RequestParam(required = false) String titulo) {
//		try {
//			List<Autor> lista = autorService.findByTitulo(titulo);
//
//			if (lista.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(lista, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
