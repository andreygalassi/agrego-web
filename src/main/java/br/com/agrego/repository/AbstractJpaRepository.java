package br.com.agrego.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
public abstract class AbstractJpaRepository<T, ID, I extends JpaRepository<T, ID>> {

	@PersistenceContext
	private EntityManager em;

	protected final I repository;

	protected AbstractJpaRepository(I repository) {
		this.repository = repository;
	}
	
	public I getIRepo(){
		return this.repository;
	}

	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	public List<T> findAll() {
		return repository.findAll();
	}
	
	public Page<T> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public T save(T entity) {
		return repository.save(entity);
	}

	public List<T> saveAll(List<T> entities) {
		return repository.saveAll(entities);
	}

	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
	public void deleteAll() {
		repository.deleteAll();;
	}

	public boolean existsById(ID id) {
		return repository.existsById(id);
	}

	EntityManager getEm() {
		return em;
	}
	
}
