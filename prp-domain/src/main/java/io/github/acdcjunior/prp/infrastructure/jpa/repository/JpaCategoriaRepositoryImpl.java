package io.github.acdcjunior.prp.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.categoria.CategoriaRepository;
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