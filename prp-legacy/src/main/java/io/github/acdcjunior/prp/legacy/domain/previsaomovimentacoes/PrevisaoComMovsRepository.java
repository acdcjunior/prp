package io.github.acdcjunior.prp.legacy.domain.previsaomovimentacoes;

import io.github.acdcjunior.prp.legacy.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.legacy.domain.previsao.Previsao;
import io.github.acdcjunior.prp.legacy.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrevisaoComMovsRepository {
	
	@Autowired
	private PrevisaoRepository previsaoRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	public PrevisaoComMovs findById(int idPrevisao) {
        Previsao previsao = previsaoRepository.findById(idPrevisao);
        return createPrevisaoComMovs(previsao);
	}

    private PrevisaoComMovs createPrevisaoComMovs(Previsao previsao) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByRealiza(previsao);
        return new PrevisaoComMovs(previsao, movimentacoes);
    }

    public List<PrevisaoComMovs> findAll() {
        List<Previsao> previsoes = previsaoRepository.findAll();
        List<PrevisaoComMovs> pms = new ArrayList<>(previsoes.size());
        for (Previsao previsao : previsoes) {
            pms.add(createPrevisaoComMovs(previsao));
        }
        return pms;
    }

}