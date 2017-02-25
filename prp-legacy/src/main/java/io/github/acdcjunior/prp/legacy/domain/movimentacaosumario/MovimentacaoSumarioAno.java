package io.github.acdcjunior.prp.legacy.domain.movimentacaosumario;

import java.util.List;

public class MovimentacaoSumarioAno {
	
	private int ano;
	private List<MovimentacaoSumarioMes> meses;
	
	MovimentacaoSumarioAno(int ano, List<MovimentacaoSumarioMes> meses) {
		this.ano = ano;
		this.meses = meses;
		verificarSeMesesPertencemAoAno();
	}
	
	private void verificarSeMesesPertencemAoAno() {
		for (MovimentacaoSumarioMes movimentacaoSumarioMes : meses) {
			if (movimentacaoSumarioMes.getAno() != this.ano) {
				throw new RuntimeException("Sumario de Mes passado nao pertence a este ano! -> "+movimentacaoSumarioMes);
			}
		}
	}

	public int getAno() {
		return ano;
	}
	public List<MovimentacaoSumarioMes> getMeses() {
		return meses;
	}
	
}