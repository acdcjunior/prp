package io.github.acdcjunior.prp.domain.previsaomovimentacoes;

import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import io.github.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrevisaoComMovimentacoesFactory {
	
	@Autowired
	private PrevisaoRepository previsaoRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	public List<PrevisaoComMovimentacoes> criarPrevisoesDaCategoria(int ano, int mes, Categoria categoria) {
		List<Previsao> previsoes = previsaoRepository.findByAnoMesCategoria(ano, mes, categoria);
		
		List<PrevisaoComMovimentacoes> pms = new ArrayList<>(previsoes.size());
		for (Previsao previsao : previsoes) {
			pms.add(criarPrevisaoComMovimentacoes(previsao));
		}
		return pms;
	}

	public PrevisaoComMovimentacoes criarPrevisaoComMovimentacoes(Previsao previsao) {
		List<Movimentacao> movimentacoes = movimentacaoRepository.findByRealiza(previsao);
		return new PrevisaoComMovimentacoes(previsao, movimentacoes);
	}

}