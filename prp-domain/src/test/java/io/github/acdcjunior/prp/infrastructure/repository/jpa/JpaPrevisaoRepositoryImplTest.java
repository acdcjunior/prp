package io.github.acdcjunior.prp.infrastructure.repository.jpa;

import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import io.github.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import io.github.acdcjunior.prp.infrastructure.jpa.repository.JpaCategoriaRepositoryImpl;
import io.github.acdcjunior.prp.infrastructure.jpa.repository.JpaPrevisaoRepositoryImpl;
import io.github.acdcjunior.prp.test.InjetarEntityManagerRule;
import io.github.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JpaPrevisaoRepositoryImplTest {
	
	@Rule
	public InjetarEntityManagerRule emRule = new InjetarEntityManagerRule();

	@InjetarEntityManager
	CategoriaRepository jpaCategoriaRepositoryImpl = new JpaCategoriaRepositoryImpl();
	
	@InjetarEntityManager
	PrevisaoRepository jpaPrevisaoRepositoryImpl = new JpaPrevisaoRepositoryImpl();

	@Test
	public void findByAnoMesCategoria() {
		Categoria categoria = jpaCategoriaRepositoryImpl.findById(4);
		List<Previsao> previsoes = jpaPrevisaoRepositoryImpl.findByAnoMesCategoria(2011, 1, categoria);
		assertThat(previsoes, hasSize(2));
	}
	
	@Test
	public void sumByAnoMesCategoria() {
		Categoria categoria = jpaCategoriaRepositoryImpl.findById(4);
		BigDecimal sum = jpaPrevisaoRepositoryImpl.sumByAnoMesCategoria(2011, 1, categoria);
		assertThat(sum, is(new BigDecimal("-600.00")));
	}

}