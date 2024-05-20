package br.com.agrego.endpoint;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrego.model.Livro;
import br.com.agrego.service.LivroService;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LivroEndpoint {
	
	@Autowired
	LivroService livroService;

	@GetMapping("/livro")
	public ResponseEntity<List<Livro>> findAll() {
		try {
			List<Livro> findAll = livroService.findAll();

			if (findAll.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/livro/{id}")
	public ResponseEntity<Livro> getById(@PathVariable("id") long id) {
		Optional<Livro> livro = livroService.findById(id);

		if (livro.isPresent()) {
			return new ResponseEntity<>(livro.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/livro")
	public ResponseEntity<Livro> create(@RequestBody Livro livro) {
		try {
			Livro livroSalvo = livroService.save(livro);
			return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/livro/{id}")
	public ResponseEntity<Livro> update(@PathVariable("id") long id, @RequestBody Livro livro) {
		Optional<Livro> oBean = livroService.findById(id);

		if (oBean.isPresent()) {
			Livro bean = oBean.get(); //TODO copiar dados para bean
			return new ResponseEntity<>(livroService.save(bean), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/livro/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			livroService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/livro")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			livroService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	@GetMapping("/livro")
//	public ResponseEntity<List<Livro>> findByTitulo(@RequestParam(required = false) String titulo) {
//		try {
//			List<Livro> lista = livroService.findByTitulo(titulo);
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
