package br.com.agrego.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByTituloContaining(String titulo);
}
