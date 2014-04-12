package net.acdcjunior.prp.domain.saldo;

import java.math.BigDecimal;

import net.acdcjunior.prp.domain.valueobject.Mes;

public class Saldo {
	
	private Mes mes;
	private BigDecimal saldoInicial;
	private BigDecimal saldoFinal;
	private boolean baseadoNaUltimaMovimentacao;
	
	Saldo(Mes mes, BigDecimal saldoInicial, BigDecimal saldoFinal, boolean baseadoNaUltimaMovimentacao) {
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