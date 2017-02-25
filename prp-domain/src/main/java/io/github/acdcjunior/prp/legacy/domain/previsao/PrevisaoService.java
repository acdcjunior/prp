package io.github.acdcjunior.prp.legacy.domain.previsao;

import io.github.acdcjunior.prp.legacy.domain.ImpossivelExcluirException;
import io.github.acdcjunior.prp.legacy.domain.categoria.Categoria;
import io.github.acdcjunior.prp.legacy.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrevisaoService {
	
	@Autowired
	private PrevisaoRepository previsaoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Transactional
	public void salvar(Previsao previsao) {
		Categoria categoria = categoriaRepository.findById(previsao.getCategoria().getId());
		previsao.setCategoria(categoria);
		previsaoRepository.save(previsao);
	}
	
	@Transactional
	public void excluir(int previsaoId) throws ImpossivelExcluirException {
		Previsao previsao = previsaoRepository.findById(previsaoId);
		if (previsao == null) {
			throw new ImpossivelExcluirException("Previsao " + previsaoId + " nao encontrada.");
		}
		List<Movimentacao> movimentacoesDaPrevisao = movimentacaoRepository.findByRealiza(previsao);
		if (!movimentacoesDaPrevisao.isEmpty()) {
			throw new ImpossivelExcluirException("Ainda existem "+movimentacoesDaPrevisao.size()+" movimentacoes associadas a esta previsao.");
		}
		previsaoRepository.remove(previsao);
	}

	@Transactional
	public Previsao realizar(Integer idPrevisao) {
		return atualizarPropriedadeRealizadaDaPrevisao(idPrevisao, true);
	}

	@Transactional
	public Previsao desrealizar(Integer idPrevisao) {
		return atualizarPropriedadeRealizadaDaPrevisao(idPrevisao, false);
	}
	
	private Previsao atualizarPropriedadeRealizadaDaPrevisao(Integer idPrevisao, boolean realizada) {
		Previsao previsao = previsaoRepository.findById(idPrevisao);
		previsao.setRealizada(realizada);
		previsaoRepository.save(previsao);
		return previsao;
	}
	
}