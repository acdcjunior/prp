package net.acdcjunior.prp.domain.previsaomovimentacoes;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.previsao.Previsao;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;
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