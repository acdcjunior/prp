package net.acdcjunior.prp.domain.movimentacao;

import java.math.BigDecimal;
import java.util.List;

import net.acdcjunior.prp.domain.BaseRepository;
import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.previsao.Previsao;

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

}