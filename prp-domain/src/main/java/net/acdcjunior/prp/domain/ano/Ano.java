package net.acdcjunior.prp.domain.ano;

import java.math.BigDecimal;
import java.util.List;

import net.acdcjunior.prp.domain.categorianoano.CategoriaNoAno;
import net.acdcjunior.prp.domain.saldo.Saldo;

public class Ano {
	
	private int ano;
	private List<CategoriaNoAno> linhas;
	
	private List<BigDecimal> diferencaPlanejada;
	private List<MovimentacoesNaoCategorizadasNoMes> movimentacoesNaoCategorizadasNosMeses;
	
	private List<Saldo> saldos;
	
	Ano(int numeroAno, List<CategoriaNoAno> linhas, List<BigDecimal> totaisPlanejados, List<MovimentacoesNaoCategorizadasNoMes> movimentacoesNaoCategorizadasNosMeses, List<Saldo> saldos) {
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

	public List<Saldo> getSaldos() {
		return saldos;
	}
	
}