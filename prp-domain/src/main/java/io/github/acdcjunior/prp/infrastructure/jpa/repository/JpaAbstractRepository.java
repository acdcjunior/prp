package io.github.acdcjunior.prp.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.domain.BaseEntity;
import io.github.acdcjunior.prp.domain.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class JpaAbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void save(T t) {
		if (t.isNew()) {
			this.em.persist(t);
		} else {
			this.em.merge(t);
		}
	}

	@Override
	public void remove(T t) {
		this.em.remove(t);
	}

	@Override
	public T findById(Integer id) {
		return this.em.find(implEntityClass(), id);
	}

	@Override
	public List<T> findAll() {
		return this.em.createQuery("FROM " + implEntityClass().getSimpleName(), implEntityClass()).getResultList();
	}

	@SuppressWarnings("unchecked")
	private Class<T> implEntityClass() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	protected <X> X getSingleResultOrNull(TypedQuery<X> query) {
		List<X> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}

}