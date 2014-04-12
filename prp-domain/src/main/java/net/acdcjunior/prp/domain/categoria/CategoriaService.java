package net.acdcjunior.prp.domain.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.acdcjunior.prp.domain.ImpossivelExcluirException;
import net.acdcjunior.prp.domain.previsao.Previsao;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;

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