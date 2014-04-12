package net.acdcjunior.prp.domain.categorianoano;

import java.util.ArrayList;
import java.util.List;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import net.acdcjunior.prp.domain.previsaomovimentacoes.PrevisaoComMovimentacoes;
import net.acdcjunior.prp.domain.previsaomovimentacoes.PrevisaoComMovimentacoesFactory;
import net.acdcjunior.prp.domain.valueobject.Mes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaNoAnoFactory {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	@Autowired
	private PrevisaoRepository previsaoRepository;
	@Autowired
	private PrevisaoComMovimentacoesFactory previsaoComMovimentacoesFactory;
	
	public CategoriaNoAno createCategoriaNoAno(int ano, Categoria categoria) {
		List<CategoriaNoMes> categoriaNosMeses = criarCategoriaNosMesesParaAno(ano, categoria);
		return new CategoriaNoAno(ano, categoria, categoriaNosMeses);
	}

	private List<CategoriaNoMes> criarCategoriaNosMesesParaAno(int ano, Categoria categoria) {
		List<CategoriaNoMes> celulas = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
		for (int iMes = 1; iMes <= Mes.QTD_MESES_NO_ANO; iMes++) {
			celulas.add(createCategoriaNoMes(ano, iMes, categoria));
		}
		return celulas;
	}

	public CategoriaNoMes createCategoriaNoMes(int ano, int mes, Categoria categoria) {
		List<PrevisaoComMovimentacoes> pms = previsaoComMovimentacoesFactory.criarPrevisoesDaCategoria(ano, mes, categoria);
		return new CategoriaNoMes(ano, mes, categoria, pms);
	}

}