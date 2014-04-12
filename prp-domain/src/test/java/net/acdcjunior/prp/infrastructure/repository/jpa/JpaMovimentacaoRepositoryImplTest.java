package net.acdcjunior.prp.infrastructure.repository.jpa;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.movimentacao.Origem;
import net.acdcjunior.prp.domain.movimentacao.OrigemRepository;
import net.acdcjunior.prp.test.InjetarEntityManagerRule;
import net.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;

import org.junit.Rule;
import org.junit.Test;

public class JpaMovimentacaoRepositoryImplTest {
	
	@Rule
	public InjetarEntityManagerRule emRule = new InjetarEntityManagerRule();
	
	@InjetarEntityManager
	CategoriaRepository jpaCategoriaRepositoryImpl = new JpaCategoriaRepositoryImpl();
	
	@InjetarEntityManager
	MovimentacaoRepository jpaMovimentacaoRepositoryImpl = new JpaMovimentacaoRepositoryImpl();
	
	@InjetarEntityManager
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
		assertThat(count, equalTo(7L));
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
	
}