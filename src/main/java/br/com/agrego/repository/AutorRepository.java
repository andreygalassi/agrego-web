package br.com.agrego.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Autor;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

interface IAutorRepository extends JpaRepository<Autor, Long> {
	List<Autor> findByNomeContaining(String nome);
}
@Repository
public class AutorRepository extends AbstractJpaRepository<Autor, Long> {

	protected AutorRepository(IAutorRepository repo) {
		super(repo);
	}

	public Page<Autor> findByFiltro(Pageable page, Autor filtro) {
		StringBuilder jpql = new StringBuilder("SELECT b FROM Autor b WHERE 1=1");
		StringBuilder countJpql = new StringBuilder("SELECT COUNT(b) FROM Autor b WHERE 1=1");

		Map<String, Object> params = new HashMap<>();
		
		if (filtro.getId() != null) {
			String sql = " AND b.id LIKE :id ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("id", filtro.getId());
		}

		if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
			String sql = " AND LOWER(b.nome) LIKE LOWER(:nome) ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("nome", "%" + filtro.getNome() + "%");
		}

		if (page.getSort().isSorted()) {
			jpql.append(" ORDER BY ");
			jpql.append(page.getSort().stream()
					.map(order -> "b." + order.getProperty() + " " + order.getDirection().name())
					.reduce((a, b) -> a + ", " + b).orElse(""));
		}

		TypedQuery<Autor> query = getEm().createQuery(jpql.toString(), Autor.class);
		Query countQuery = getEm().createQuery(countJpql.toString());

		params.forEach((k, v) -> {
			query.setParameter(k, v);
			countQuery.setParameter(k, v);
		});

		// Paginação
		query.setFirstResult((int) page.getOffset());
		query.setMaxResults(page.getPageSize());

		List<Autor> result = query.getResultList();
		long total = (Long) countQuery.getSingleResult();

		return new PageImpl<>(result, page, total);
	}

}
