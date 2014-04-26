package net.acdcjunior.prp.domain.ano.categorianoano;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import net.acdcjunior.prp.domain.ano.categorianoano.CategoriaNoMes;
import net.acdcjunior.prp.domain.previsaomovimentacoes.PrevisaoComMovimentacoes;

import org.junit.Before;
import org.junit.Test;

public class CategoriaNoMesTest {

	List<PrevisaoComMovimentacoes> pcms;
	
	@Before
	public void setUp() {
		PrevisaoComMovimentacoes pcm2 = mock(PrevisaoComMovimentacoes.class);
		when(pcm2.getValorPrevisao()).thenReturn(BigDecimal.TEN);
		when(pcm2.getQtdMovimentacoes()).thenReturn(2);
		PrevisaoComMovimentacoes pcm4 = mock(PrevisaoComMovimentacoes.class);
		when(pcm4.getValorPrevisao()).thenReturn(BigDecimal.TEN);
		when(pcm4.getQtdMovimentacoes()).thenReturn(4);
		PrevisaoComMovimentacoes pcm7 = mock(PrevisaoComMovimentacoes.class);
		when(pcm7.getValorPrevisao()).thenReturn(BigDecimal.TEN);
		when(pcm7.getQtdMovimentacoes()).thenReturn(7);
		
		this.pcms = Arrays.asList(pcm2, pcm4, pcm7);
	}
	
	@Test
	public void getQtdMovimentacoes__deve_retornar_quantidade_de_movimentacoes_baseado_nas_previsoes_recebidas() {
		// given
		// when
		CategoriaNoMes categoriaNoMes = new CategoriaNoMes(1, 1, null, this.pcms);
		// then
		assertThat(categoriaNoMes, hasProperty("qtdMovimentacoes", equalTo(13)));
	}
	
	@Test
	public void getQtdPrevisoes__deve_retornar_quantidade_de_previsoes_baseado_nas_previsoes_recebidas() {
		// given
		// when
		CategoriaNoMes categoriaNoMes = new CategoriaNoMes(1, 1, null, this.pcms);
		// then
		assertThat(categoriaNoMes, hasProperty("qtdPrevisoes", equalTo(3)));
	}

}