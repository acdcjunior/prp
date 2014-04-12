package net.acdcjunior.prp.domain.ano;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.categorianoano.CategoriaNoAno;
import net.acdcjunior.prp.domain.categorianoano.CategoriaNoAnoFactory;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.saldo.Saldo;
import net.acdcjunior.prp.domain.saldo.SaldoFactory;
import net.acdcjunior.prp.domain.valueobject.Mes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnoFactory {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	@Autowired
	private CategoriaNoAnoFactory categoriaNoAnoFactory;
	@Autowired
	private SaldoFactory saldoFactory;

	public Ano createAno(int numeroAno) {
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaNoAno> linhas = new ArrayList<>(categorias.size());
		
    	for (Categoria categoria : categorias) {
    		linhas.add(categoriaNoAnoFactory.createCategoriaNoAno(numeroAno, categoria));
    	}
    	
    	List<BigDecimal> totaisPlanejados = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
    	List<MovimentacoesNaoCategorizadasNoMes> movimentacoesNaoCategorizadasNosMeses = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
    	
    	List<Saldo> saldos = new ArrayList<>(Mes.QTD_MESES_NO_ANO);
    	
    	for (int iMes = 1; iMes <= Mes.QTD_MESES_NO_ANO; iMes++) {
    		MovimentacoesNaoCategorizadasNoMes movimentacoesNaoCategorizadasNoMes = createMovimentacoesNaoCategorizadasNoMes(numeroAno, iMes);
			movimentacoesNaoCategorizadasNosMeses.add(movimentacoesNaoCategorizadasNoMes);
    		
    		BigDecimal valorPreviso = totalValoresPrevistos(iMes, linhas);
        	totaisPlanejados.add(movimentacoesNaoCategorizadasNoMes.getSomaValor().add(valorPreviso));
    		
        	Saldo saldo = saldoFactory.getSaldo(numeroAno, iMes);
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