package br.com.agrego.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Perfil;
import br.com.agrego.model.dto.PerfilFiltro;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

interface IPerfilRepository extends JpaRepository<Perfil, Long> {}

@Repository
public class PerfilRepository extends AbstractJpaRepository<Perfil, Long, IPerfilRepository> {

	protected PerfilRepository(IPerfilRepository repo) {
		super(repo);
	}

	public Page<Perfil> findByFiltro(Pageable page, PerfilFiltro filtro) {
		StringBuilder jpql = new StringBuilder("SELECT b FROM Perfil b WHERE 1=1");
		StringBuilder countJpql = new StringBuilder("SELECT COUNT(b) FROM Perfil b WHERE 1=1");

		Map<String, Object> params = new HashMap<>();
		
		if (filtro.getId() != null) {
			String sql = " AND b.id = :id ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("id", filtro.getId());
		}

		if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
			String sql = " AND UPPER(b.nome) LIKE UPPER(:nome) ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("nome", "%" + filtro.getNome() + "%");
		}
		
		if (filtro.getListaAcesso() != null && !filtro.getListaAcesso().isEmpty()) {
			String sql = " AND listaAcesso.id in (:listaAcesso) ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("listaAcesso", filtro.getListaAcesso().stream().map(x -> x.getId()).collect(Collectors.toSet()));
		}

		if (page.getSort().isSorted()) {
			jpql.append(" ORDER BY ");
			jpql.append(page.getSort().stream()
					.map(order -> "b." + order.getProperty() + " " + order.getDirection().name())
					.reduce((a, b) -> a + ", " + b).orElse(""));
		}

		TypedQuery<Perfil> query = getEm().createQuery(jpql.toString(), Perfil.class);
		Query countQuery = getEm().createQuery(countJpql.toString());

		params.forEach((k, v) -> {
			query.setParameter(k, v);
			countQuery.setParameter(k, v);
		});

		// Paginacao
		query.setFirstResult((int) page.getOffset());
		query.setMaxResults(page.getPageSize());

		List<Perfil> result = query.getResultList();
		long total = (Long) countQuery.getSingleResult();

		return new PageImpl<>(result, page, total);
	}

}
