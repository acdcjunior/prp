package io.github.acdcjunior.prp.domain.movimentacaosumario;

import io.github.acdcjunior.prp.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import io.github.acdcjunior.prp.test.ObjectBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovimentacaoSumarioMesTest {

	@Test
	public void construtor__deve_contabilizar_a_quantidade_e_valor() {
		// GIVEN
		int anoBase = 2014;
		int mesBase = 1;
		LocalDate dataBase = LocalDate.of(anoBase, mesBase, 15);

		ObjectBuilder<Movimentacao> movimentacaoBase = new ObjectBuilder<>(Movimentacao.class).with("data", dataBase);
		BigDecimal valorCredito1 = new BigDecimal(123.78);
		BigDecimal valorCredito2 = new BigDecimal(63457.99);
		Movimentacao credito1 = movimentacaoBase.with("valor", valorCredito1).build();
		Movimentacao credito2 = movimentacaoBase.with("valor", valorCredito2).build();
		BigDecimal valorDebito1 = new BigDecimal(-342.77);
		BigDecimal valorDebito2 = new BigDecimal(-1235.23);
		Movimentacao debito1 = movimentacaoBase.with("valor", valorDebito1).build();
		Movimentacao debito2 = movimentacaoBase.with("valor", valorDebito2).build();
		List<Movimentacao> movimentacoes = Arrays.asList(credito1, debito1, credito2, debito2);
		// WHEN
		MovimentacaoSumarioMes msm = new MovimentacaoSumarioMes(anoBase, mesBase, movimentacoes);
		// THEN
		assertEquals(anoBase, msm.getAno());
		assertEquals(mesBase, msm.getMes());
		assertEquals(2, msm.getQtdMovimentacoesCredito());
		assertEquals(2, msm.getQtdMovimentacoesDebito());
		assertEquals(valorCredito1.add(valorCredito2), msm.getSomaMovimentacoesCredito());
		assertEquals(valorDebito1.add(valorDebito2), msm.getSomaMovimentacoesDebito());
		assertEquals(valorCredito1.add(valorCredito2).add(valorDebito1).add(valorDebito2), msm.getDiferenca());
	}
	
	@Test(expected=RuntimeException.class)
	public void construtor__deve_lancar_excecao_caso_a_data_de_uma_das_movimentacoes_passadas_nao_pertenca_ao_mes_do_sumario() {
		// GIVEN
		int anoBase = 2014;
		int mesBase = 1;
        LocalDate dataEmFevereiro = LocalDate.of(anoBase, mesBase+1, 15);
		
		ObjectBuilder<Movimentacao> movimentacaoBase = new ObjectBuilder<>(Movimentacao.class).with("data", dataEmFevereiro).with("valor", new BigDecimal(999.99));
		Movimentacao movimentacaoComDataDeFevereiro = movimentacaoBase.build();
		List<Movimentacao> movimentacoes = Arrays.asList(movimentacaoComDataDeFevereiro);
		// WHEN
		new MovimentacaoSumarioMes(anoBase, mesBase, movimentacoes);
		// THEN
		// deve lancar excecao
	}
	
	@Test
	public void construtor__deve_contabilizar_a_quantidade_de_categorizadas() {
		// GIVEN
		int anoBase = 2014;
		int mesBase = 1;
        LocalDate dataBase = LocalDate.of(anoBase, mesBase, 15);
		
		ObjectBuilder<Movimentacao> movimentacaoBase = new ObjectBuilder<>(Movimentacao.class).with("data", dataBase).with("valor", BigDecimal.ONE);
		Movimentacao m1 = movimentacaoBase.with("realiza", new Previsao()).build();
		Movimentacao m2 = movimentacaoBase.with("realiza", new Previsao()).build();
		Movimentacao m3 = movimentacaoBase.with("realiza", null).build();
		List<Movimentacao> movimentacoes = Arrays.asList(m1, m2, m3);
		// WHEN
		MovimentacaoSumarioMes msm = new MovimentacaoSumarioMes(anoBase, mesBase, movimentacoes);
		// THEN
		assertEquals(2, msm.getQtdCategorizada());
		assertEquals(1, msm.getQtdNaoCategorizada());
	}

}