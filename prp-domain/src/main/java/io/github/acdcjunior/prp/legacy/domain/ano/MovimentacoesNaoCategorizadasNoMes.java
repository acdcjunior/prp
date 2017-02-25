package io.github.acdcjunior.prp.legacy.domain.ano;

import java.math.BigDecimal;

public class MovimentacoesNaoCategorizadasNoMes {
	
	private int ano;
	private int mes;
	private Long quantidade;
	private BigDecimal somaValor;
	
	MovimentacoesNaoCategorizadasNoMes(int ano, int mes, Long quantidade, BigDecimal somaValor) {
		this.ano = ano;
		this.mes = mes;
		this.quantidade = quantidade;
		this.somaValor = somaValor;
	}
	
	public int getAno() {
		return ano;
	}
	public int getMes() {
		return mes;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public BigDecimal getSomaValor() {
		return somaValor;
	}

}