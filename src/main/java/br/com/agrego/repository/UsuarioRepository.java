package br.com.agrego.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Usuario;

interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByLogin(String username);
}

@Repository
public class UsuarioRepository extends AbstractJpaRepository<Usuario, Long, IUsuarioRepository> {

	protected UsuarioRepository(IUsuarioRepository repo) {
		super(repo);
	}

	public Optional<Usuario> findByLogin(String username) {
		return getIRepo().findByLogin(username);
	}

}
