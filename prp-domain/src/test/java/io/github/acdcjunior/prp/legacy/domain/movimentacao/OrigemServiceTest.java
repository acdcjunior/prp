package io.github.acdcjunior.prp.legacy.domain.movimentacao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrigemServiceTest {
	
	@Mock
	private OrigemRepository origemRepository;
	
	@Mock
	MovimentacaoRepository movimentacaoRepository;
	
	@Mock
	MovimentacaoFactory movimentacaoFactory;
	
	@InjectMocks
	OrigemService origemService;
	
	
	Movimentacao m0 = new Movimentacao();
	Movimentacao m1 = new Movimentacao();
	Movimentacao m2 = new Movimentacao();
	List<Movimentacao> movimentacoes = Arrays.asList(m0, m1, m2);
	
	Movimentacao m1factory = mock(Movimentacao.class);
	Movimentacao m2factory = mock(Movimentacao.class);
	
	Integer idOrigemAReceber = 1;
	Origem origem = mock(Origem.class, "Origem #1"); 
	
	@Before
	public void setUp() {
		when(origem.getId()).thenReturn(idOrigemAReceber);
		when(origemRepository.findById(idOrigemAReceber)).thenReturn(origem);
		when(movimentacaoRepository.findUltimaMovimentacaoByOrigem(origem)).thenReturn(m0);

		ReflectionTestUtils.setField(m0, "id", 100);

		m0.setOrigem(origem);
		m1.setOrigem(origem);
		m2.setOrigem(origem);
		
		when(movimentacaoFactory.criar(m1, m0)).thenReturn(m1factory);
		when(movimentacaoFactory.criar(m2, m1factory)).thenReturn(m2factory);
	}
	
	@Test
	public void criarMovimentacoes__deve_salvar_todas_as_movimentacoes_passadas_com_excecao_da_primeira__e__as_movimentacoes_salvas_sao_proveniente_da_factory() throws Exception {
		// given
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, movimentacoes);
		// then
		verify(movimentacaoRepository, times(2)).save(any(Movimentacao.class));
		verify(movimentacaoRepository).save(m1factory);
		verify(movimentacaoRepository).save(m2factory);
	}
	
	@Test
	public void criarMovimentacoes__deve_chamar_o_criar_da_factory_com_a_movimentacao_imediatamente_anterior_a_ela_na_lista_como_movimentacaoBase() throws Exception {
		// given
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, movimentacoes);
		// then
		verify(movimentacaoFactory).criar(m1, m0);
		verify(movimentacaoFactory).criar(m2, m1factory);
	}
	
	@Test(expected=OrigemNaoEquivalenteException.class)
	public void criarMovimentacoes__deve_receber_uma_origem_e_uma_lista_de_movimentacoes_e_lancar_excecao_quando_alguma_delas_nao_tiver_a_origem_com_o_id_passado() throws Exception {
		// given
		Movimentacao m11 = new Movimentacao();
		Origem outraOrigem = mock(Origem.class, "Outra origem qualquer");
		when(outraOrigem.getId()).thenReturn(idOrigemAReceber + 1);
		m11.setOrigem(outraOrigem);
		List<Movimentacao> movimentacoesComMaisUma = new ArrayList<Movimentacao>(Arrays.asList(m0, m1, m11, m2)); 
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, movimentacoesComMaisUma);
		// then
		// deve lancar excecao
	}
	
	@Test
	public void criarMovimentacoes__deve_receber_uma_origem_e_uma_lista_de_movimentacoes_e_NAO_lancar_excecao_quando_alguma_delas_TIVER_a_origem_com_o_ID_passado() throws Exception {
		// given
		Movimentacao m11 = new Movimentacao();
		Origem outraOrigem = mock(Origem.class, "Outra origem qualquer");
		when(outraOrigem.getId()).thenReturn(idOrigemAReceber);
		m11.setOrigem(outraOrigem);
		List<Movimentacao> movimentacoesComMaisUma = new ArrayList<Movimentacao>(Arrays.asList(m0, m1, m11, m2)); 
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, movimentacoesComMaisUma);
		// then
		// deve NAO lancar excecao
	}
	
	@Test
	public void criarMovimentacoes__deve_receber_uma_origem_e_uma_lista_de_movimentacoes_e_aceitar_movimentacoes_com_origem_nula() throws Exception {
		// given
		Movimentacao mOrigemNula = mock(Movimentacao.class);
		List<Movimentacao> movimentacoesComMaisUma = new ArrayList<Movimentacao>(Arrays.asList(m0, m1, mOrigemNula, m2)); 
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, movimentacoesComMaisUma);
		// then
		// deve **NAO** lancar excecao
	}

	@Test(expected=MovimentacaoBaseNaoEhAUltimaDaOrigemException.class)
	public void criarMovimentacoes__deve_receber_uma_lista_de_movimentacoes_e_lancar_excecao_quando_a_primeira_da_lista_nao_for_a_ultima_da_origem() throws Exception {
		// given
		when(movimentacaoRepository.findUltimaMovimentacaoByOrigem(origem)).thenReturn(mock(Movimentacao.class, "outra movimentacao que nao m0"));
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, movimentacoes);
		// then
		// deve lancar excecao
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void criarMovimentacoes__deve_lancar_excecao_caso_lista_de_movimentacoes_seja_vazia() throws Exception {
		// given
		// when
		origemService.criarMovimentacoes(idOrigemAReceber, new ArrayList<Movimentacao>());
		// then
		// deve lancar excecao
	}

}