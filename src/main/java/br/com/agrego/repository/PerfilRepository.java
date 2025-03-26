package br.com.agrego.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.enuns.EnumRole;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	Optional<Perfil> findByRole(EnumRole role);
}
