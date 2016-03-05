package net.acdcjunior.prp.domain.previsao;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.acdcjunior.prp.domain.previsaomovimentacoes.PrevisaoComMovimentacoes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PrevisaoServiceTest {
	
	@Mock
	PrevisaoRepository previsaoRepository;
	
	@InjectMocks
	PrevisaoService previsaoService;
	
	@Mock
	Previsao previsaoMock;
	@Mock
	PrevisaoComMovimentacoes previsaoComMovimentacoesMock;
	
	Integer idPrevisao = 1;
	
	@Before
	public void setUp() {
		when(previsaoRepository.findById(idPrevisao)).thenReturn(previsaoMock);
	}
	
	@Test
	public void realizar__deve_marcar_como_realizada_a_previsao_do_id_passado__e__retornar_o_valor_da_previsao_atual() {
		// given
		// when
		previsaoService.realizar(idPrevisao);
		// then
		verify(previsaoMock).setRealizada(true);
		verify(previsaoRepository).save(previsaoMock);
	}
	
	@Test
	public void desrealizar__deve_marcar_como_realizada_a_previsao_do_id_passado__e__retornar_o_valor_da_previsao_atual() {
		// given
		// when
		previsaoService.desrealizar(idPrevisao);
		// then
		verify(previsaoMock).setRealizada(false);
		verify(previsaoRepository).save(previsaoMock);
	}

}