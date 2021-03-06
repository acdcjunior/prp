package io.github.acdcjunior.prp.legacy.infrastructure.repository.jpa;

import io.github.acdcjunior.prp.legacy.domain.categoria.Categoria;
import io.github.acdcjunior.prp.legacy.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.Origem;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.OrigemRepository;
import io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository.JpaCategoriaRepositoryImpl;
import io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository.JpaMovimentacaoRepositoryImpl;
import io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository.JpaOrigemRepositoryImpl;
import io.github.acdcjunior.prp.legacy.test.InjetarEntityManagerRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Ignore("problemas ao criar PU, mas, em tese, o teste funciona 100%! (funcionava no maven)")
public class JpaMovimentacaoRepositoryImplTest {
	
	@Rule
	public InjetarEntityManagerRule emRule = new InjetarEntityManagerRule();
	
	@InjetarEntityManagerRule.InjetarEntityManager
    CategoriaRepository jpaCategoriaRepositoryImpl = new JpaCategoriaRepositoryImpl();
	
	@InjetarEntityManagerRule.InjetarEntityManager
    MovimentacaoRepository jpaMovimentacaoRepositoryImpl = new JpaMovimentacaoRepositoryImpl();
	
	@InjetarEntityManagerRule.InjetarEntityManager
    OrigemRepository jpaOrigemRepositoryImpl = new JpaOrigemRepositoryImpl();

	@Test
	public void save() {
		Movimentacao m = new Movimentacao();
		m.setDescricao2("ball");
		emRule.begin();
		jpaMovimentacaoRepositoryImpl.save(m);
		emRule.commit();
		
		Movimentacao m2 = jpaMovimentacaoRepositoryImpl.findById(m.getId());
		assertNotNull(m2);
	}

	@Test
	public void findById() {
		Movimentacao m = jpaMovimentacaoRepositoryImpl.findById(1);
		assertNotNull(m.getNumeroDocumento());
		assertNotNull(m.getOrigem());
		assertNotNull(m.getRealiza());
	}
	
	@Test
	public void propriedades_anterior_e_seguinte_devem_estar_corretamente_mapeadas() {
		// given
		// when
		Movimentacao m = jpaMovimentacaoRepositoryImpl.findById(240);
		// then
		assertThat(m.getAnterior().getId(), is(equalTo(239)));
		assertThat(m.getAnteriorId(), is(equalTo(239)));
		
		assertThat(m.getSeguinte().getId(), is(equalTo(241)));
		assertThat(m.getSeguinte().getAnteriorId(), is(equalTo(240)));
	}

	@Test
	public void findAll() {
		List<Movimentacao> findAll = jpaMovimentacaoRepositoryImpl.findAll();
		assertFalse(findAll.isEmpty());
	}
	
	@Test
	public void sumValorByPrevisaoAnoMesCategoria() {
		Categoria categoria = jpaCategoriaRepositoryImpl.findById(17);
		BigDecimal soma = jpaMovimentacaoRepositoryImpl.sumValorByPrevisaoAnoMesCategoria(2011, 4, categoria);
		assertThat(soma, is(new BigDecimal("-173.56")));
	}
	
	@Test
	public void sumValorByAnoMesSemRealizacao__deve_trazer_a_soma_das_movimentacoes_nao_associadas_a_previsao_daquele_ano() {
		// given
		int ano = 2012;
		int mes = 1;
		// when
		BigDecimal soma = jpaMovimentacaoRepositoryImpl.sumValorByAnoMesSemRealizacao(ano, mes);
		// then
		assertThat(soma, equalTo(new BigDecimal("112.06")));
	}
	
	@Test
	public void sumValorByAnoMesSemRealizacao__deve_trazer_ZERO_se_nao_encontrar_nada() {
		// given
		int ano = 2099;
		int mes = 2;
		// when
		BigDecimal soma = jpaMovimentacaoRepositoryImpl.sumValorByAnoMesSemRealizacao(ano, mes);
		// then
		assertThat(soma, equalTo(BigDecimal.ZERO));
	}
	
	@Test
	public void countByAnoMesSemRealizacao__deve_trazer_a_soma_das_movimentacoes_nao_associadas_a_previsao_daquele_ano() {
		// given
		int ano = 2011;
		int mes = 1;
		// when
		Long count = jpaMovimentacaoRepositoryImpl.countByAnoMesSemRealizacao(ano, mes);
		// then
		assertThat(count, equalTo(13L));
	}

	@Test
	public void findPrimeiraMovimentacaoDoMes() {
		// given
		// when
		Movimentacao m = jpaMovimentacaoRepositoryImpl.findPrimeiraMovimentacaoDoMes(2011, 1);
		// then
		assertThat(m.getId(), is(equalTo(178)));
		assertThat(m.getDescricao1(), is(equalTo("BOLATO: PRIMEIRA DE 2011/01")));
	}
	
	@Test
	public void findUltimaMovimentacaoDoMes() {
		// given
		// when
		Movimentacao m = jpaMovimentacaoRepositoryImpl.findUltimaMovimentacaoDoMes(2011, 1);
		// then
		assertThat(m.getId(), is(equalTo(206)));
		assertThat(m.getDescricao1(), is(equalTo("SALARIO - 2011-01-25")));
	}
	
	@Test
	public void findPrimeiraMovimentacaoDoMes__deve_retornar_null_quando_mes_nao_tiver_movimentacoes() {
		// given
		// when
		Movimentacao m = jpaMovimentacaoRepositoryImpl.findPrimeiraMovimentacaoDoMes(2020, 3);
		// then
		assertThat(m, is(nullValue()));
	}
	
	@Test
	public void findUltimaMovimentacaoDoMes__deve_retornar_null_quando_mes_nao_tiver_movimentacoes() {
		// given
		// when
		Movimentacao m = jpaMovimentacaoRepositoryImpl.findUltimaMovimentacaoDoMes(2020, 3);
		// then
		assertThat(m, is(nullValue()));
	}
	
	@Test
	public void findAnos__deve_trazer_lista_de_anos_ordenada_de_forma_ascendente() {
		// given
		// when
		List<Integer> anos = jpaMovimentacaoRepositoryImpl.findAnos();
		// then
		assertThat(anos, contains(2010, 2011, 2012));
	}
	
	@Test
	public void findUltimaMovimentacaoByOrigem() {
		// given
		Origem o = jpaOrigemRepositoryImpl.findById(1);
		// when
		Movimentacao ultimaMov = jpaMovimentacaoRepositoryImpl.findUltimaMovimentacaoByOrigem(o);
		// then
		assertThat(ultimaMov, hasProperty("descricao1", is(equalTo("MOCK"))));
	}


    @Test
    public void findAllPorListaEncadeadaAnterior() {
        // given
        // when
        List<Movimentacao> movimentacoes = jpaMovimentacaoRepositoryImpl.findAllPorListaEncadeadaAnterior();
        // then

        List<Origem> origens = movimentacoes.stream().map(Movimentacao::getOrigem).distinct().collect(Collectors.toList());
        for (Origem o : origens) {

            List<Movimentacao> movsDestaOrigem = movimentacoes.stream().filter(m -> m.getOrigem().equals(o)).collect(Collectors.toList());

            Iterator<Movimentacao> iterator = movsDestaOrigem.iterator();
            Movimentacao m = iterator.next();
            Integer id = m.getId();
            assertThat(m.getAnteriorId(), is(nullValue()));
            while (iterator.hasNext()) {
                m = iterator.next();
                assertThat(m.getAnteriorId(), is(id));
                id = m.getId();
            }

        }


    }
	
}