package net.acdcjunior.prp.domain.ano.saldodomesnoano;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.acdcjunior.prp.domain.ano.categorianoano.CategoriaNoAnoFactory;
import net.acdcjunior.prp.domain.ano.categorianoano.CategoriaNoMes;
import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import net.acdcjunior.prp.domain.valueobject.Mes;

@Component
public class SaldoDoMesNoAnoFactory {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private PrevisaoRepository previsaoRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	@Autowired
	private CategoriaNoAnoFactory categoriaNoAnoFactory;

	private Map<String, SaldoDoMesNoAno> saldos = new HashMap<>();
	
	public void resetSaldos() {
		this.saldos = new HashMap<>();
	}
	
	public SaldoDoMesNoAno getSaldo(int ano, int mes) {
		String anoMes = ano + "-" + mes;
		SaldoDoMesNoAno saldo = saldos.get(anoMes);
		if (saldo == null) {
			Mes m = new Mes(ano, mes);
			saldo = createSaldo(m);
			saldos.put(anoMes, saldo);
		}
		return saldo;
	}
	
	private SaldoDoMesNoAno createSaldo(Mes mes) {
		List<Categoria> categorias = categoriaRepository.findAll();
		
		BigDecimal saldoInicial;
		boolean baseadoNaUltimaMovimentacao;
		
		BigDecimal saldoUltimaMovimentacaoDoMesAnterior = getSaldoDaUltimaMovimentacaoDoMesAnterior(mes);
		boolean existeSaldoRealMesAnterior = saldoUltimaMovimentacaoDoMesAnterior != null;
		if (existeSaldoRealMesAnterior) {
			saldoInicial = saldoUltimaMovimentacaoDoMesAnterior;
			baseadoNaUltimaMovimentacao = true;
		} else {
			saldoInicial = getPrevisaoSaldoFinalMesAnterior(mes);
			baseadoNaUltimaMovimentacao = false;
		}
		
		BigDecimal saldoFinal = calcularSaldoFinal(mes, categorias, saldoInicial);
		
		return new SaldoDoMesNoAno(mes, saldoInicial, saldoFinal, baseadoNaUltimaMovimentacao);
	}
	

	public BigDecimal getSaldoDaUltimaMovimentacaoDoMesAnterior(Mes mes) {
		Mes mesAnterior = mes.getMesAnterior();
		Movimentacao ultimaMov = movimentacaoRepository.findUltimaMovimentacaoDoMes(mesAnterior.getAno(), mesAnterior.getMes());
		return ultimaMov == null ? null : ultimaMov.getSaldo();
	}
	
	private BigDecimal getPrevisaoSaldoFinalMesAnterior(Mes mes) {
		Mes mesAnterior = mes.getMesAnterior();
		return getSaldo(mesAnterior.getAno(), mesAnterior.getMes()).getSaldoFinal();
	}
	
	private BigDecimal calcularSaldoFinal(Mes mes, List<Categoria> categorias, BigDecimal saldoInicial) {
		BigDecimal saldoFinal = saldoInicial;
		for (Categoria categoria : categorias) {
			CategoriaNoMes catNoMes = categoriaNoAnoFactory.createCategoriaNoMes(mes.getAno(), mes.getMes(), categoria);
			saldoFinal = saldoFinal.add(catNoMes.getValor());
		}
		return saldoFinal;
	}

}
