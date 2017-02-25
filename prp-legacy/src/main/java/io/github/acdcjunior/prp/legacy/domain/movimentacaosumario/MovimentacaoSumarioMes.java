package io.github.acdcjunior.prp.legacy.domain.movimentacaosumario;

import io.github.acdcjunior.prp.legacy.domain.movimentacao.Movimentacao;

import java.math.BigDecimal;
import java.util.List;

public class MovimentacaoSumarioMes {
	
	private int ano;
	private int mes;
	private int qtdMovimentacoesCredito = 0;
	private int qtdMovimentacoesDebito = 0;
	private BigDecimal somaMovimentacoesCredito = BigDecimal.ZERO;
	private BigDecimal somaMovimentacoesDebito = BigDecimal.ZERO;
	private int qtdCategorizada = 0;
	
	MovimentacaoSumarioMes(int ano, int mes, List<Movimentacao> movimentacoesDoMes) {
		this.ano = ano;
		this.mes = mes;
		adicionarMovimentacoes(movimentacoesDoMes);
	}

	private void adicionarMovimentacoes(List<Movimentacao> movimentacoesDoMes) {
        movimentacoesDoMes.stream()
                .filter(this::movimentacaoPertenceAoMes)
                .forEach(this::adicionarMovimentacao);
	}
	
	private boolean movimentacaoPertenceAoMes(Movimentacao movimentacao) {
		boolean ehDoAno = movimentacao.getData().getYear() == ano;
		boolean ehDoMes = movimentacao.getData().getMonthValue() == mes;
		if (!ehDoAno || !ehDoMes) {
			throw new RuntimeException("Movimentacao enviada eh nao pertence ao mes do sumario! -> "+movimentacao);
		}
		return true;
	}

	private void adicionarMovimentacao(Movimentacao movimentacao) {
		boolean ehCredito = movimentacao.getValor().signum() > 0;
		if (ehCredito) {
			adicionaCredito(movimentacao);
		} else {
			adicionaDebito(movimentacao);
		}
		if (movimentacao.getRealiza() != null) {
			this.qtdCategorizada++;
		}
	}
	
	private void adicionaCredito(Movimentacao movimentacao) {
		this.qtdMovimentacoesCredito++;
		this.somaMovimentacoesCredito = this.somaMovimentacoesCredito.add(movimentacao.getValor());
	}
	
	private void adicionaDebito(Movimentacao movimentacao) {
		this.qtdMovimentacoesDebito++;
		this.somaMovimentacoesDebito = this.somaMovimentacoesDebito.add(movimentacao.getValor());
	}

	public int getAno() {
		return ano;
	}
	public int getMes() {
		return mes;
	}
	public int getQtdMovimentacoesCredito() {
		return qtdMovimentacoesCredito;
	}
	public int getQtdMovimentacoesDebito() {
		return qtdMovimentacoesDebito;
	}
	public BigDecimal getSomaMovimentacoesCredito() {
		return somaMovimentacoesCredito;
	}
	public BigDecimal getSomaMovimentacoesDebito() {
		return somaMovimentacoesDebito;
	}
	public BigDecimal getDiferenca() {
		return somaMovimentacoesCredito.add(somaMovimentacoesDebito);
	}
	public int getQtdCategorizada() {
		return qtdCategorizada;
	}
	public int getQtdNaoCategorizada() {
		return qtdMovimentacoesCredito+qtdMovimentacoesDebito-qtdCategorizada;
	}
	
}