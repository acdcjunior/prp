package io.github.acdcjunior.prp.legacy.domain.previsaomovimentacoes;

import io.github.acdcjunior.prp.legacy.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.legacy.domain.previsao.Previsao;

import java.math.BigDecimal;
import java.util.List;

public class PrevisaoComMovs extends Previsao {

	private List<Movimentacao> movimentacoes;
	private BigDecimal somaValorMovimentacoes = BigDecimal.ZERO;

	PrevisaoComMovs(Previsao previsao, List<Movimentacao> movimentacoes) {
        copiarPropriedadesDaPrevisao(previsao);

		this.movimentacoes = movimentacoes;
		
		for (Movimentacao movimentacao : getMovimentacoes()) {
			this.somaValorMovimentacoes = this.somaValorMovimentacoes.add(movimentacao.getValor());
		}
	}

    private void copiarPropriedadesDaPrevisao(Previsao previsao) {
        this.id = previsao.getId();
        this.setData(previsao.getData());
        this.setDescricao(previsao.getDescricao());
        this.setValor(previsao.getValor());
        this.setCategoria(previsao.getCategoria());
        this.setRealizada(previsao.isRealizada());
        this.setBill(previsao.isBill());
    }

    public List<Movimentacao> getMovimentacoes() { return movimentacoes; }
	
	public int getQtdMovimentacoes() { return movimentacoes.size(); }

	public BigDecimal getSomaValorMovimentacoes() {
		return this.somaValorMovimentacoes;
	}

	/**
	 * Retorna o valor de fato desta previsao.<br>
	 * <br>
	 * - Se ela foi realizada, retornarah a soma dos valores de suas movimentacoes.<br>
	 * - Se ela ainda nao foi, retornarah o valor previsto.
	 */
	public BigDecimal getValorFinal() {
		if (isRealizada()) {
			return getSomaValorMovimentacoes();
		}
		return this.getValor();
	}
	
}