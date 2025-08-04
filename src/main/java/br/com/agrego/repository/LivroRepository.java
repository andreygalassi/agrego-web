package br.com.agrego.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrego.model.Livro;
import br.com.agrego.model.dto.LivroFiltro;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

interface ILivroRepository extends JpaRepository<Livro, Long> {}

@Repository
public class LivroRepository extends AbstractJpaRepository<Livro, Long, ILivroRepository> {

	protected LivroRepository(ILivroRepository repo) {
		super(repo);
	}

	public Page<Livro> findByFiltro(Pageable page, LivroFiltro filtro) {
		StringBuilder jpql = new StringBuilder("SELECT b FROM Livro b WHERE 1=1");
		StringBuilder countJpql = new StringBuilder("SELECT COUNT(b) FROM Livro b WHERE 1=1");

		Map<String, Object> params = new HashMap<>();
		
		if (filtro.getId() != null) {
			String sql = " AND b.id = :id ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("id", filtro.getId());
		}

		if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
			String sql = " AND UPPER(b.titulo) LIKE UPPER(:titulo) ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("titulo", "%" + filtro.getTitulo() + "%");
		}
		
		if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
			String sql = " AND UPPER(b.descricao) LIKE UPPER(:descricao) ";
			jpql.append(sql);
			countJpql.append(sql);
			params.put("descricao", "%" + filtro.getDescricao() + "%");
		}
		
		if (filtro.getListaAutor() != null && !filtro.getListaAutor().isEmpty()) {
			StringBuilder sql = new StringBuilder();
			int i = 0;
			for (String autor : filtro.getListaAutor()) {
				sql.append("OR UPPER(b.autor.nome) LIKE UPPER(:autor"+i+")");
				params.put("autor"+i, "%"+autor+"%");
				i++;
			}
			
			jpql.append(" AND ( ").append(sql.toString().substring(3)).append(" ) ");
			countJpql.append(" AND ( ").append(sql.toString().substring(3)).append(" ) ");
		}

		if (page.getSort().isSorted()) {
			jpql.append(" ORDER BY ");
			jpql.append(page.getSort().stream()
					.map(order -> "b." + order.getProperty() + " " + order.getDirection().name())
					.reduce((a, b) -> a + ", " + b).orElse(""));
		}

		TypedQuery<Livro> query = getEm().createQuery(jpql.toString(), Livro.class);
		Query countQuery = getEm().createQuery(countJpql.toString());

		params.forEach((k, v) -> {
			query.setParameter(k, v);
			countQuery.setParameter(k, v);
		});

		// Paginação
		query.setFirstResult((int) page.getOffset());
		query.setMaxResults(page.getPageSize());

		List<Livro> result = query.getResultList();
		long total = (Long) countQuery.getSingleResult();

		return new PageImpl<>(result, page, total);
	}
}
