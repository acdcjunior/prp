package io.github.acdcjunior.prp.legacy.domain.movimentacao;

import io.github.acdcjunior.prp.legacy.domain.previsao.Previsao;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovimentacaoFactoryTest {
	
	MovimentacaoFactory movimentacaoFactory = new MovimentacaoFactory();

	@Test
	public void criar__deve_criar_uma_movimentacao_com_as_propriedades_passadas__apontando_para_a_outra_mov_passada_e_calculando_a_origem_e_saldo_com_base_nesta() {
		// given
		LocalDate date = LocalDate.of(2012, 1, 29);
		String numeroDocumento = "numero documento";
		String descricao1 = "descricao1";
		String descricao2 = "descricao2";
		BigDecimal valor = new BigDecimal("456.88");
		Previsao previsao = mock(Previsao.class);
		Origem origemMovBase = mock(Origem.class);
		BigDecimal saldoMovBase = new BigDecimal("56568.54");
		
		Movimentacao movimentacaoDados = mock(Movimentacao.class);
		when(movimentacaoDados.getData()).thenReturn(date);
		when(movimentacaoDados.getNumeroDocumento()).thenReturn(numeroDocumento);
		when(movimentacaoDados.getDescricao1()).thenReturn(descricao1);
		when(movimentacaoDados.getDescricao2()).thenReturn(descricao2);
		when(movimentacaoDados.getValor()).thenReturn(valor);
		when(movimentacaoDados.getRealiza()).thenReturn(previsao);
		
		Movimentacao movimentacaoBase = mock(Movimentacao.class);
		when(movimentacaoBase.getOrigem()).thenReturn(origemMovBase);
		when(movimentacaoBase.getSaldo()).thenReturn(saldoMovBase);
		// when
		Movimentacao movimentacaoCriada = movimentacaoFactory.criar(movimentacaoDados, movimentacaoBase);
		// then
		assertThat(movimentacaoCriada.getData(), equalTo(date));
		assertThat(movimentacaoCriada.getNumeroDocumento(), equalTo(numeroDocumento));
		assertThat(movimentacaoCriada.getDescricao1(), equalTo(descricao1));
		assertThat(movimentacaoCriada.getDescricao2(), equalTo(descricao2));
		assertThat(movimentacaoCriada.getValor(), equalTo(valor));
		assertThat(movimentacaoCriada.getRealiza(), equalTo(previsao));
		assertThat(movimentacaoCriada.getAnterior(), equalTo(movimentacaoBase));
		assertThat(movimentacaoCriada.getOrigem(), equalTo(origemMovBase));
		assertThat(movimentacaoCriada.getSaldo(), equalTo(saldoMovBase.add(valor)));
	}

}