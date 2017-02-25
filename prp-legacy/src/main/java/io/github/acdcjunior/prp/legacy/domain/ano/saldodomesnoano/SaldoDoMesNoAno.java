package io.github.acdcjunior.prp.legacy.domain.ano.saldodomesnoano;

import io.github.acdcjunior.prp.legacy.domain.valueobject.Mes;

import java.math.BigDecimal;

public class SaldoDoMesNoAno {
	
	private Mes mes;
	private BigDecimal saldoInicial;
	private BigDecimal saldoFinal;
	private boolean baseadoNaUltimaMovimentacao;
	
	SaldoDoMesNoAno(Mes mes, BigDecimal saldoInicial, BigDecimal saldoFinal, boolean baseadoNaUltimaMovimentacao) {
		this.mes = mes;
		this.saldoInicial = saldoInicial;
		this.saldoFinal = saldoFinal;
		this.baseadoNaUltimaMovimentacao = baseadoNaUltimaMovimentacao;
	}
	
	public Mes getMes() {
		return mes;
	}

	public BigDecimal getSaldoInicial() {
		return this.saldoInicial;
	}
	
	public BigDecimal getSaldoFinal() {
		return this.saldoFinal;
	}
	
	public BigDecimal getDiferencaSaldo() {
		return this.saldoInicial.subtract(this.saldoFinal);
	}

	public boolean isBaseadoNaUltimaMovimentacao() {
		return baseadoNaUltimaMovimentacao;
	}
	
}