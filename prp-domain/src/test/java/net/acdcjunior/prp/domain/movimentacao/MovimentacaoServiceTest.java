package net.acdcjunior.prp.domain.movimentacao;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovimentacaoServiceTest {
	
	@Mock
	MovimentacaoRepository movimentacaoRepository;
	
	@InjectMocks
	MovimentacaoService movimentacaoService;

	@SuppressWarnings("unchecked")
	@Test
	public void getMovimentacoesMesComLimitrofes__deve_trazer_movimentacoes_do_mes__e__ultima_do_mes_anterior__e__primeira_do_mes_seguinte_se_existirem() {
		// given
		int ano = 2014, mes = 7;
		Movimentacao ultimaMesAnterior = mock(Movimentacao.class); when(ultimaMesAnterior.isNotInMonth(ano, mes)).thenReturn(true);
		Movimentacao primeiraMesAtual = mock(Movimentacao.class); when(primeiraMesAtual.isNotInMonth(ano, mes)).thenReturn(false);
		Movimentacao segundaMesAtual = mock(Movimentacao.class); when(segundaMesAtual.isNotInMonth(ano, mes)).thenReturn(false);
		Movimentacao primeiraMesSeguinte = mock(Movimentacao.class); when(primeiraMesSeguinte.isNotInMonth(ano, mes)).thenReturn(true);
		
		when(ultimaMesAnterior.getSeguinte()).thenReturn(primeiraMesAtual);
		
		when(primeiraMesAtual.getAnterior()).thenReturn(ultimaMesAnterior);
		when(primeiraMesAtual.getSeguinte()).thenReturn(segundaMesAtual);
		
		when(segundaMesAtual.getAnterior()).thenReturn(primeiraMesAtual);
		when(segundaMesAtual.getSeguinte()).thenReturn(primeiraMesSeguinte);
		
		when(primeiraMesSeguinte.getAnterior()).thenReturn(segundaMesAtual);
		
		when(movimentacaoRepository.findByAnoMes(ano, mes)).thenReturn(asList(primeiraMesAtual, segundaMesAtual));
		// when
		Map<String, Object> resultado = movimentacaoService.getMovimentacoesMesComLimitrofes(ano, mes);
		// then
		assertThat(resultado, hasEntry("ultimaMesAnterior", (Object) ultimaMesAnterior));
		assertThat(resultado, hasKey("movimentacoesMes"));
		assertThat(resultado.get("movimentacoesMes"), instanceOf(List.class));
		assertThat((List<Movimentacao>) resultado.get("movimentacoesMes"), hasItems(primeiraMesAtual, segundaMesAtual));
		assertThat(resultado, hasEntry("primeiraMesSeguinte", (Object) primeiraMesSeguinte));
	}

}