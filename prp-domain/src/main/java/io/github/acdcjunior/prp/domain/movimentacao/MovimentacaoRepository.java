package io.github.acdcjunior.prp.domain.movimentacao;

import io.github.acdcjunior.prp.domain.BaseRepository;
import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.previsao.Previsao;

import java.math.BigDecimal;
import java.util.List;

public interface MovimentacaoRepository extends BaseRepository<Movimentacao> {

	List<Movimentacao> findByRealiza(Previsao previsao);

	List<Movimentacao> findByAno(int ano);
	
	List<Movimentacao> findByAnoMes(int ano, int mes);

	BigDecimal sumValorByRealiza(Previsao previsao);
	
	List<Movimentacao> findByPrevisaoAnoMesCategoria(int ano, int mes, Categoria categoria);
	
	BigDecimal sumValorByPrevisaoAnoMesCategoria(int ano, int mes, Categoria categoria);
	
	BigDecimal sumValorByAnoMesSemRealizacao(int ano, int mes);
	
	Long countByAnoMesSemRealizacao(int ano, int mes);

	Movimentacao findPrimeiraMovimentacaoDoMes(int ano, int mes);

	Movimentacao findUltimaMovimentacaoDoMes(int ano, int mes);

	Movimentacao findUltimaMovimentacaoByOrigem(Origem origem);
	
	List<Integer> findAnos();

	List<Movimentacao> findByAnoMesSemCategoria(int ano, int mes);

    List<Movimentacao> findAllPorListaEncadeadaAnterior();

}