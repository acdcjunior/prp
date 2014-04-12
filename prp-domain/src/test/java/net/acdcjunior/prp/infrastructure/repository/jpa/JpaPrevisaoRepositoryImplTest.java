package net.acdcjunior.prp.infrastructure.repository.jpa;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.previsao.Previsao;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import net.acdcjunior.prp.infrastructure.repository.jpa.JpaCategoriaRepositoryImpl;
import net.acdcjunior.prp.infrastructure.repository.jpa.JpaPrevisaoRepositoryImpl;
import net.acdcjunior.prp.test.InjetarEntityManagerRule;
import net.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;

import org.junit.Rule;
import org.junit.Test;

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