package net.acdcjunior.prp.domain.previsaomovimentacoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.previsao.Previsao;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;

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