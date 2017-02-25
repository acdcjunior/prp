package io.github.acdcjunior.prp.legacy.domain.ano.categorianoano;

import io.github.acdcjunior.prp.legacy.domain.categoria.Categoria;
import io.github.acdcjunior.prp.legacy.domain.previsaomovimentacoes.PrevisaoComMovimentacoes;

import java.math.BigDecimal;
import java.util.List;

public class CategoriaNoMes {
	
	private int ano;
	private int mes;
	private Categoria categoria;
	private List<PrevisaoComMovimentacoes> previsoes;
	private int qtdMovimentacoes;
	private BigDecimal valor;
	private boolean previsao;
	
	CategoriaNoMes(int ano, int mes, Categoria categoria, List<PrevisaoComMovimentacoes> previsoes) {
		this.ano = ano;
		this.mes = mes;
		this.categoria = categoria;
		this.previsoes = previsoes;
		
		carregarValor();
		contarMovimentacoes(previsoes);
	}

	private void carregarValor() {
		this.valor = BigDecimal.ZERO;
		this.previsao = false;
		for (PrevisaoComMovimentacoes pm : previsoes) {
			this.valor = this.valor.add(pm.getValorPrevisao());
			if (!pm.isRealizada()) {
				this.previsao = true;
			}
		}
	}

	private void contarMovimentacoes(List<PrevisaoComMovimentacoes> previsoes) {
		this.qtdMovimentacoes = 0;
		for (PrevisaoComMovimentacoes previsaoComMovimentacoes : previsoes) {
			this.qtdMovimentacoes += previsaoComMovimentacoes.getQtdMovimentacoes();
		}
	}
	
	public int getAno() {
		return ano;
	}
	public int getMes() {
		return mes;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public boolean isPrevisao() {
		return previsao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public int getQtdPrevisoes() {
		return previsoes.size();
	}
	public int getQtdMovimentacoes() {
		return qtdMovimentacoes;
	}

}