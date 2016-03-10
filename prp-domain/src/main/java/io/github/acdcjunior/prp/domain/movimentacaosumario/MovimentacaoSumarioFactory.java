package io.github.acdcjunior.prp.domain.movimentacaosumario;

import io.github.acdcjunior.prp.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovimentacaoSumarioFactory {
	
	private static final int QTD_MESES_ANO = 12;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	public MovimentacaoSumarioAno createMovimentacaoSumarioAno(int ano) {
		List<MovimentacaoSumarioMes> meses = new ArrayList<>(QTD_MESES_ANO);
		for (int mes = 1; mes <= QTD_MESES_ANO; mes++) {
			meses.add(createMovimentacaoSumarioMes(ano, mes)); 
		}
		return new MovimentacaoSumarioAno(ano, meses);
	}
	
	public MovimentacaoSumarioMes createMovimentacaoSumarioMes(int ano, int mes) {
		List<Movimentacao> movimentacoesDoMes = movimentacaoRepository.findByAnoMes(ano, mes);
		return new MovimentacaoSumarioMes(ano, mes, movimentacoesDoMes);
	}

}