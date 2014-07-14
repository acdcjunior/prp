package net.acdcjunior.prp.infrastructure.repository.jpa;

import java.math.BigDecimal;
import java.util.List;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.previsao.Previsao;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaPrevisaoRepositoryImpl extends JpaAbstractRepository<Previsao> 
									   implements PrevisaoRepository {

	private static final String FIND_BY_CATEGORIA = "Previsao.findByCategoria";
	private static final String FIND_BY_ANO_MES_CATEGORIA = "Previsao.findByAnoMesCategoria";
	private static final String FIND_BY_ANO_MES = "Previsao.findByAnoMes";
	private static final String FIND_BY_ANO = "Previsao.findByAno";
	private static final String SUM_VALOR_BY_ANO_MES_CATEGORIA = "Previsao.sumValorByAnoMesCategoria";

    @Override
    public List<Previsao> findAll() {
        return this.em.createQuery("FROM Previsao p order by p.data", Previsao.class).getResultList();
    }
	
	@Override
	public List<Previsao> findByCategoria(Categoria categoria) {
		return this.em.createNamedQuery(FIND_BY_CATEGORIA, Previsao.class)
				.setParameter("categoria", categoria)
				.getResultList();
	}
	
	@Override
	public List<Previsao> findByAnoMesCategoria(int ano, int mes, Categoria categoria) {
		return this.em.createNamedQuery(FIND_BY_ANO_MES_CATEGORIA, Previsao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.setParameter("categoria", categoria)
				.getResultList();
	}

	@Override
	public BigDecimal sumByAnoMesCategoria(int ano, int mes, Categoria categoria) {
		BigDecimal soma = this.em.createNamedQuery(SUM_VALOR_BY_ANO_MES_CATEGORIA, BigDecimal.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.setParameter("categoria", categoria)
				.getSingleResult();
		return soma != null ? soma : BigDecimal.ZERO;
	}

	@Override
	public List<Previsao> findByAnoMes(int ano, int mes) {
		return this.em.createNamedQuery(FIND_BY_ANO_MES, Previsao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.getResultList();
	}

	@Override
	public List<Previsao> findByAno(int ano) {
		return this.em.createNamedQuery(FIND_BY_ANO, Previsao.class)
				.setParameter("ano", ano)
				.getResultList();
	}

}