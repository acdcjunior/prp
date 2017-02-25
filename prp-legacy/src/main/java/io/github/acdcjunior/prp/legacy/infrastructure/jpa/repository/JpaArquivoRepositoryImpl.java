package io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.legacy.arquivo.Arquivo;
import io.github.acdcjunior.prp.legacy.arquivo.ArquivoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaArquivoRepositoryImpl extends JpaAbstractRepository<Arquivo>
									implements ArquivoRepository {
	
	@Override
	public List<Arquivo> findAll() {
		return this.em.createQuery("FROM Arquivo a order by a.dataReferencia", Arquivo.class).getResultList();
	}

}