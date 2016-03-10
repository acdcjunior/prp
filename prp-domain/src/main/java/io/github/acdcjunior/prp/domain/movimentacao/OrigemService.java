package io.github.acdcjunior.prp.domain.movimentacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
public class OrigemService {
	
	@Autowired
	private OrigemRepository origemRepository;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Autowired
	private MovimentacaoFactory movimentacaoFactory;
	
	@Transactional
	public void criarMovimentacoes(Integer idOrigemAReceber, List<Movimentacao> movimentacoes) throws OrigemNaoEquivalenteException, MovimentacaoBaseNaoEhAUltimaDaOrigemException {
		if (movimentacoes.isEmpty()) {
			throw new IllegalArgumentException("Lista de movimentacoes é vazia!");
		}
		
		Origem origem = origemRepository.findById(idOrigemAReceber);
		verificarMovimentacaoBaseEhAUltimaDaOrigem(movimentacoes, origem);
		verificarTodasMovimentacoesPertencemAaOrigem(movimentacoes, origem);
		
		Iterator<Movimentacao> iterator = movimentacoes.iterator();
		Movimentacao movimentacaoAnterior = iterator.next();
		while (iterator.hasNext()) {
			Movimentacao movimentacaoDados = iterator.next();
			Movimentacao movimentacaoCriada = movimentacaoFactory.criar(movimentacaoDados, movimentacaoAnterior);
			movimentacaoRepository.save(movimentacaoCriada);
			movimentacaoAnterior = movimentacaoCriada;
		}
	}

	private void verificarMovimentacaoBaseEhAUltimaDaOrigem(List<Movimentacao> movimentacoes, Origem origem) throws MovimentacaoBaseNaoEhAUltimaDaOrigemException {
		Movimentacao ultimaMovimentacaoDaOrigem = movimentacaoRepository.findUltimaMovimentacaoByOrigem(origem);
		if (!ultimaMovimentacaoDaOrigem.getId().equals(movimentacoes.get(0).getId())) {
			throw new MovimentacaoBaseNaoEhAUltimaDaOrigemException();
		}
	}

	private void verificarTodasMovimentacoesPertencemAaOrigem(List<Movimentacao> movimentacoes, Origem origem) throws OrigemNaoEquivalenteException {
		for (Movimentacao movimentacao : movimentacoes) {
			if (isMovimentacaoComOrigemInvalida(origem, movimentacao)) {
				throw new OrigemNaoEquivalenteException();
			}
		}
	}

	private boolean isMovimentacaoComOrigemInvalida(Origem origemEsperada, Movimentacao movimentacao) {
		if (movimentacao.getOrigem() == null) {
			return false;
		}
		if (origemEsperada.getId().equals(movimentacao.getOrigem().getId())) {
			return false;
		}
		return true;
	}

}