package br.com.agrego.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Perfil;

interface IPerfilRepository extends JpaRepository<Perfil, Long> {}

@Repository
public class PerfilRepository extends AbstractJpaRepository<Perfil, Long, IPerfilRepository> {

	protected PerfilRepository(IPerfilRepository repo) {
		super(repo);
	}

}
