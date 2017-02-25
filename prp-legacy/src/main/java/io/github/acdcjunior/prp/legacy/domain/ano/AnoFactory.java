package io.github.acdcjunior.prp.legacy.domain.ano;

import io.github.acdcjunior.prp.legacy.domain.ano.categorianoano.CategoriaNoAno;
import io.github.acdcjunior.prp.legacy.domain.ano.categorianoano.CategoriaNoAnoFactory;
import io.github.acdcjunior.prp.legacy.domain.ano.saldodomesnoano.SaldoDoMesNoAno;
import io.github.acdcjunior.prp.legacy.domain.ano.saldodomesnoano.SaldoDoMesNoAnoFactory;
import io.github.acdcjunior.prp.legacy.domain.categoria.Categoria;
import io.github.acdcjunior.prp.legacy.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.legacy.domain.valueobject.Mes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnoFactory {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	@Autowired
	private CategoriaNoAnoFactory categoriaNoAnoFactory;
	@Autowired
	private SaldoDoMesNoAnoFactory saldoFactory;

	public Ano createAno(int numeroAno) {
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaNoAno> linhas = new ArrayList<>(categorias.size());
		
    	for (Categoria categoria : categorias) {
    		linhas.add(categoriaNoAnoFactory.createCategoriaNoAno(numeroAno, categoria));
    	}
    	
    	List<BigDecimal> totaisPlanejados = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
    	List<MovimentacoesNaoCategorizadasNoMes> movimentacoesNaoCategorizadasNosMeses = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
    	
    	List<SaldoDoMesNoAno> saldos = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
    	
    	for (int iMes = 1; iMes <= Mes.QTD_MESES_NO_ANO; iMes++) {
    		MovimentacoesNaoCategorizadasNoMes movimentacoesNaoCategorizadasNoMes = createMovimentacoesNaoCategorizadasNoMes(numeroAno, iMes);
			movimentacoesNaoCategorizadasNosMeses.add(movimentacoesNaoCategorizadasNoMes);
    		
    		BigDecimal valorPreviso = totalValoresPrevistos(iMes, linhas);
        	totaisPlanejados.add(movimentacoesNaoCategorizadasNoMes.getSomaValor().add(valorPreviso));
    		
        	SaldoDoMesNoAno saldo = saldoFactory.getSaldo(numeroAno, iMes);
        	saldos.add(saldo);
		}
    	return new Ano(numeroAno, linhas, totaisPlanejados, movimentacoesNaoCategorizadasNosMeses, saldos);
	}
	
	private BigDecimal totalValoresPrevistos(int mes, List<CategoriaNoAno> linhas) {
		BigDecimal tr = BigDecimal.ZERO;
		for (CategoriaNoAno linha : linhas) {
			tr = tr.add(linha.getValorMes(mes));
		}
		return tr;
	}
	
	public MovimentacoesNaoCategorizadasNoMes createMovimentacoesNaoCategorizadasNoMes(int numeroAno, int iMes) {
		BigDecimal somaValorNaoCategorizadasNoMes = movimentacaoRepository.sumValorByAnoMesSemRealizacao(numeroAno, iMes);
		Long quantidadeNaoCategorizadasNoMes = movimentacaoRepository.countByAnoMesSemRealizacao(numeroAno, iMes);
		
		return new MovimentacoesNaoCategorizadasNoMes(numeroAno, iMes, quantidadeNaoCategorizadasNoMes, somaValorNaoCategorizadasNoMes);
	}
	
}