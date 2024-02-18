package br.com.agrego.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
	List<Autor> findByNomeContaining(String nome);
}
