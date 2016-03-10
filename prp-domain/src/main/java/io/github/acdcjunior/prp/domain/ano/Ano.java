package io.github.acdcjunior.prp.domain.ano;

import io.github.acdcjunior.prp.domain.ano.categorianoano.CategoriaNoAno;
import io.github.acdcjunior.prp.domain.ano.saldodomesnoano.SaldoDoMesNoAno;

import java.math.BigDecimal;
import java.util.List;

public class Ano {
	
	private int ano;
	private List<CategoriaNoAno> linhas;
	
	private List<BigDecimal> diferencaPlanejada;
	private List<MovimentacoesNaoCategorizadasNoMes> movimentacoesNaoCategorizadasNosMeses;
	
	private List<SaldoDoMesNoAno> saldos;
	
	Ano(int numeroAno, List<CategoriaNoAno> linhas, List<BigDecimal> totaisPlanejados, List<MovimentacoesNaoCategorizadasNoMes> movimentacoesNaoCategorizadasNosMeses, List<SaldoDoMesNoAno> saldos) {
		this.ano = numeroAno;
		this.linhas = linhas;
		this.diferencaPlanejada = totaisPlanejados;
		this.movimentacoesNaoCategorizadasNosMeses = movimentacoesNaoCategorizadasNosMeses;
		this.saldos = saldos;
	}

	public int getAno() {
		return ano;
	}
	
	public List<CategoriaNoAno> getLinhas() {
		return this.linhas;
	}
	
	public List<BigDecimal> getDiferencaPlanejada() {
		return diferencaPlanejada;
	}

	public List<MovimentacoesNaoCategorizadasNoMes> getMovimentacoesNaoCategorizadasNosMeses() {
		return movimentacoesNaoCategorizadasNosMeses;
	}

	public List<SaldoDoMesNoAno> getSaldos() {
		return saldos;
	}
	
}