package io.github.acdcjunior.prp.legacy.domain.categoria;

import io.github.acdcjunior.prp.legacy.domain.ImpossivelExcluirException;
import io.github.acdcjunior.prp.legacy.domain.previsao.Previsao;
import io.github.acdcjunior.prp.legacy.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private PrevisaoRepository previsaoRepository;

	@Transactional
	public void excluir(int categoriaId) throws ImpossivelExcluirException {
		Categoria categoria = categoriaRepository.findById(categoriaId);
		if (categoria == null) {
			throw new ImpossivelExcluirException("Categoria " + categoriaId + " nao encontrada.");
		}
		List<Previsao> previsoesDaCategoria = previsaoRepository.findByCategoria(categoria);
		if (!previsoesDaCategoria.isEmpty()) {
			throw new ImpossivelExcluirException("Ainda existem "+previsoesDaCategoria.size()+" previsoes associadas a esta categoria.");
		}
		categoriaRepository.remove(categoria);
	}

}