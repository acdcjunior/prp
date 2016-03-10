package io.github.acdcjunior.prp.domain.movimentacao;

import io.github.acdcjunior.prp.domain.previsao.Previsao;
import io.github.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovimentacaoService {
	
	@Autowired
	private PrevisaoRepository previsaoRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Transactional
	public void alterarRealiza(int idMovimentacao, int idPrevisao) {
		Previsao previsao = previsaoRepository.findById(idPrevisao);
		Movimentacao m = movimentacaoRepository.findById(idMovimentacao);
		m.setRealiza(previsao);
		movimentacaoRepository.save(m);
	}

	@Transactional
	public Movimentacao update(Movimentacao movimentacao) {
		if (movimentacao.isNew()) {
			throw new IllegalArgumentException("update() somente deve ser chamado para movimentacoes nao novas!");
		}
		Integer id = movimentacao.getId();
		Movimentacao mov = movimentacaoRepository.findById(id);
		mov.setData(movimentacao.getData());
		mov.setNumeroDocumento(movimentacao.getNumeroDocumento());
		mov.setDescricao1(movimentacao.getDescricao1());
		mov.setDescricao2(movimentacao.getDescricao2());
		mov.setRealiza(movimentacao.getRealiza());
		movimentacaoRepository.save(mov);
		return mov;
	}

	@Transactional
	public void save(List<Movimentacao> movimentacoes) {
		for (Movimentacao movimentacao : movimentacoes) {
			movimentacaoRepository.save(movimentacao);
		}
	}
	
	@Transactional
	public Map<String, Object> getMovimentacoesMesComLimitrofes(int ano, int mes) {
    	List<Movimentacao> movimentacoesDoMes = movimentacaoRepository.findByAnoMes(ano, mes);
    	Map<Integer, Movimentacao> movMap = new HashMap<Integer, Movimentacao>();
    	for (Movimentacao movimentacao : movimentacoesDoMes) {
    		movMap.put(movimentacao.getId(), movimentacao);
    	}
    	Movimentacao primeira = null, ultima = null;
    	for (Movimentacao movimentacao : movimentacoesDoMes) {
    		if (movimentacao.getAnterior() == null || movimentacao.getAnterior().isNotInMonth(ano, mes)) {
				primeira = movimentacao;
			}
			if (movimentacao.getSeguinte() == null || movimentacao.getSeguinte().isNotInMonth(ano, mes)) {
				ultima = movimentacao;
			}
		}
    	
    	List<Movimentacao> movimentacoesDoMesOrdenadas = gerarMovimentacoesOrdenadas(movimentacoesDoMes, primeira, ultima);
    	
    	Map<String, Object> res = new HashMap<>();
    	res.put("ultimaMesAnterior", primeira.getAnterior());
    	res.put("primeiraMesSeguinte", ultima.getSeguinte());
    	res.put("movimentacoesMes", movimentacoesDoMesOrdenadas);
    	return res;
	}

	private List<Movimentacao> gerarMovimentacoesOrdenadas(List<Movimentacao> movimentacoesDoMes, Movimentacao primeira, Movimentacao ultima) {
		List<Movimentacao> movimentacoesDoMesOrdenadas = new ArrayList<>(movimentacoesDoMes.size()) ;
    	Movimentacao mov = primeira;
    	while (mov != ultima) {
    		movimentacoesDoMesOrdenadas.add(mov);
    		mov = mov.getSeguinte();
    	}
    	movimentacoesDoMesOrdenadas.add(ultima);
		return movimentacoesDoMesOrdenadas;
	}
	
}