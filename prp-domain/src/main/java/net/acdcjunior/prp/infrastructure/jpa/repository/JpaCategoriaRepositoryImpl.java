package net.acdcjunior.prp.infrastructure.jpa.repository;

import java.util.List;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaCategoriaRepositoryImpl extends JpaAbstractRepository<Categoria> 
									implements CategoriaRepository {
	
	@Override
	public List<Categoria> findAll() {
		return this.em.createQuery("FROM Categoria c order by c.nome", Categoria.class).getResultList();
	}

}