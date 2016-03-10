package io.github.acdcjunior.prp.infrastructure.jpa.repository;

import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.domain.movimentacao.Origem;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class JpaMovimentacaoRepositoryImpl extends JpaAbstractRepository<Movimentacao> 
										   implements MovimentacaoRepository {

	private static final String FIND_ULTIMA_MOVIMENTACAO_DO_MES = "Movimentacao.findUltimaMovimentacaoDoMes";
	private static final String FIND_PRIMEIRA_MOVIMENTACAO_DO_MES = "Movimentacao.findPrimeiraMovimentacaoDoMes";
	private static final String SUM_VALOR_BY_ANO_MES_SEM_REALIZACAO = "Movimentacao.sumValorByAnoMesSemRealizacao";
	private static final String COUNT_BY_ANO_MES_SEM_REALIZACAO = "Movimentacao.countByAnoMesSemRealizacao";
	private static final String FIND_BY_REALIZA = "Movimentacao.findByRealiza";
	private static final String SUM_VALOR_BY_REALIZA = "Movimentacao.sumValorByRealiza";
	private static final String FIND_BY_PREVISAO_ANO_MES_CATEGORIA = "Movimentacao.findByPrevisaoAnoMesCategoria";
	private static final String FIND_BY_ANO_MES_SEM_CATEGORIA = "Movimentacao.findByAnoMesSemCategoria";
	private static final String SUM_VALOR_BY_PREVISAO_ANO_MES_CATEGORIA = "Movimentacao.sumValorByPrevisaoAnoMesCategoria";
	private static final String FIND_BY_ANO = "Movimentacao.findByAno";
	private static final String FIND_BY_ANO_MES = "Movimentacao.findByAnoMes";
	private static final String FIND_ULTIMA_MOVIMENTACAO_BY_ORIGEM = "Movimentacao.findUltimaMovimentacaoByOrigem";
	private static final String FIND_ANOS = "Movimentacao.findAnos";

	@Override
	public List<Movimentacao> findByRealiza(Previsao previsao) {
		return this.em.createNamedQuery(FIND_BY_REALIZA, Movimentacao.class)
				.setParameter("previsao", previsao)
				.getResultList();
	}

	@Override
	public BigDecimal sumValorByRealiza(Previsao previsao) {
		BigDecimal soma = this.em.createNamedQuery(SUM_VALOR_BY_REALIZA, BigDecimal.class)
				.setParameter("previsao", previsao)
				.getSingleResult();
		return soma != null ? soma : BigDecimal.ZERO;
	}
	
	@Override
	public List<Movimentacao> findByPrevisaoAnoMesCategoria(int ano, int mes, Categoria categoria) {
		return this.em.createNamedQuery(FIND_BY_PREVISAO_ANO_MES_CATEGORIA, Movimentacao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.setParameter("categoria", categoria)
				.getResultList();
	}
	
	@Override
	public List<Movimentacao> findByAnoMesSemCategoria(int ano, int mes) {
		return this.em.createNamedQuery(FIND_BY_ANO_MES_SEM_CATEGORIA, Movimentacao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.getResultList();
	}

	@Override
	public BigDecimal sumValorByPrevisaoAnoMesCategoria(int ano, int mes, Categoria categoria) {
		BigDecimal soma = this.em.createNamedQuery(SUM_VALOR_BY_PREVISAO_ANO_MES_CATEGORIA, BigDecimal.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.setParameter("categoria", categoria)
				.getSingleResult();
		return soma != null ? soma : BigDecimal.ZERO;
	}
	
	@Override
	public BigDecimal sumValorByAnoMesSemRealizacao(int ano, int mes) {
		BigDecimal soma = this.em.createNamedQuery(SUM_VALOR_BY_ANO_MES_SEM_REALIZACAO, BigDecimal.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.getSingleResult();
		return soma != null ? soma : BigDecimal.ZERO;
	}
	
	@Override
	public Long countByAnoMesSemRealizacao(int ano, int mes) {
		return this.em.createNamedQuery(COUNT_BY_ANO_MES_SEM_REALIZACAO, Long.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.getSingleResult();
	}

	@Override
	public Movimentacao findPrimeiraMovimentacaoDoMes(int ano, int mes) {
		TypedQuery<Movimentacao> query = this.em.createNamedQuery(FIND_PRIMEIRA_MOVIMENTACAO_DO_MES, Movimentacao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.setMaxResults(1);
		return getSingleResultOrNull(query);
	}

	@Override
	public Movimentacao findUltimaMovimentacaoDoMes(int ano, int mes) {
		TypedQuery<Movimentacao> query = this.em.createNamedQuery(FIND_ULTIMA_MOVIMENTACAO_DO_MES, Movimentacao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.setMaxResults(1);
		return getSingleResultOrNull(query);
	}

	@Override
	public List<Movimentacao> findByAno(int ano) {
		return this.em.createNamedQuery(FIND_BY_ANO, Movimentacao.class)
				.setParameter("ano", ano)
				.getResultList();
	}

	@Override
	public List<Movimentacao> findByAnoMes(int ano, int mes) {
		return this.em.createNamedQuery(FIND_BY_ANO_MES, Movimentacao.class)
				.setParameter("ano", ano)
				.setParameter("mes", mes)
				.getResultList();
	}

	@Override
	public Movimentacao findUltimaMovimentacaoByOrigem(Origem origem) {
		TypedQuery<Movimentacao> query = this.em.createNamedQuery(FIND_ULTIMA_MOVIMENTACAO_BY_ORIGEM, Movimentacao.class)
				.setParameter("origem", origem)
				.setMaxResults(1);
		return getSingleResultOrNull(query);
	}

	@Override
	public List<Integer> findAnos() {
		return this.em.createNamedQuery(FIND_ANOS, Integer.class).getResultList();
	}

    @Override
    public List<Movimentacao> findAllPorListaEncadeadaAnterior() {
        List<Movimentacao> movimentacoes = this.findAll();


        List<Origem> origens = movimentacoes.stream().map(Movimentacao::getOrigem).distinct().collect(Collectors.toList());

        List<Movimentacao> movsOrdenadas = new LinkedList<>();
        for (Origem o : origens) {
            Map<Integer, Movimentacao> movMap = movimentacoes.stream().filter(m -> m.getOrigem().equals(o)).collect(Collectors.toMap(Movimentacao::getAnteriorId, Function.identity()));

            Movimentacao primeira = movimentacoes.stream().filter(m -> m.getOrigem().equals(o) && m.getAnterior() == null).findFirst().get();
            movsOrdenadas.add(primeira);

            Movimentacao proxima = movMap.get(primeira.getId());
            while (proxima != null) {
                movsOrdenadas.add(proxima);
                proxima = movMap.get(proxima.getId());
            }
        }
        if (movsOrdenadas.size() != movimentacoes.size()) {
            movimentacoes.removeAll(movsOrdenadas);
            throw new RuntimeException("Nao foi possivel ordenar as movimentacoes pelo campo ANTERIORID! Veio do banco pelo menos uma" +
                    " movimentacao que nao estava na lista encadeada: "+movimentacoes);
        }

        return movsOrdenadas;
    }

}