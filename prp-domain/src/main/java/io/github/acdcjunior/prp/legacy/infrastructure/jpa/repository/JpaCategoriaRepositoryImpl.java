package io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.legacy.domain.categoria.Categoria;
import io.github.acdcjunior.prp.legacy.domain.categoria.CategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCategoriaRepositoryImpl extends JpaAbstractRepository<Categoria> 
									implements CategoriaRepository {
	
	@Override
	public List<Categoria> findAll() {
		return this.em.createQuery("FROM Categoria c order by c.nome", Categoria.class).getResultList();
	}

}