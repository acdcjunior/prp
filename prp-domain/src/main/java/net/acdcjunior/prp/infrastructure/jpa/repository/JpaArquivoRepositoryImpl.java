package net.acdcjunior.prp.infrastructure.jpa.repository;

import net.acdcjunior.prp.arquivo.Arquivo;
import net.acdcjunior.prp.arquivo.ArquivoRepository;
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