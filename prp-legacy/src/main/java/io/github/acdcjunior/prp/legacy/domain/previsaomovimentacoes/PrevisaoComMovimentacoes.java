package io.github.acdcjunior.prp.legacy.domain.previsaomovimentacoes;

import io.github.acdcjunior.prp.legacy.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.legacy.domain.previsao.Previsao;

import java.math.BigDecimal;
import java.util.List;

public class PrevisaoComMovimentacoes {
	
	private Previsao previsao;
	private List<Movimentacao> movimentacoes;
	private BigDecimal somaValorMovimentacoes = BigDecimal.ZERO;
	
	PrevisaoComMovimentacoes(Previsao previsao, List<Movimentacao> movimentacoes) {
		this.previsao = previsao;
		this.movimentacoes = movimentacoes;
		
		for (Movimentacao movimentacao : getMovimentacoes()) {
			this.somaValorMovimentacoes = this.somaValorMovimentacoes.add(movimentacao.getValor());
		}
	}
	
	public Previsao getPrevisao() { return previsao; }
	
	public List<Movimentacao> getMovimentacoes() { return movimentacoes; }
	
	public int getQtdMovimentacoes() { return movimentacoes.size(); }

	public boolean isRealizada() {
		return this.previsao.isRealizada();
	}

	public BigDecimal getValorPrevisto() {
		return this.previsao.getValor();
	}

	public BigDecimal getSomaValorMovimentacoes() {
		return this.somaValorMovimentacoes;
	}

	/**
	 * Retorna o valor de fato desta previsao.<br>
	 * <br>
	 * - Se ela foi realizada, retornarah a soma dos valores de suas movimentacoes.<br>
	 * - Se ela ainda nao foi, retornarah o valor previsto.
	 */
	public BigDecimal getValorPrevisao() {
		if (isRealizada()) {
			return getSomaValorMovimentacoes();
		}
		return getValorPrevisto();
	}
	
}