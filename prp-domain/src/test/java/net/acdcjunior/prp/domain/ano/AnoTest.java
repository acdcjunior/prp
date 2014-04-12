package net.acdcjunior.prp.domain.ano;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.acdcjunior.prp.domain.ano.Ano;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.categorianoano.CategoriaNoAno;
import net.acdcjunior.prp.domain.categorianoano.CategoriaNoMes;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import net.acdcjunior.prp.infrastructure.repository.jpa.JpaCategoriaRepositoryImpl;
import net.acdcjunior.prp.infrastructure.repository.jpa.JpaMovimentacaoRepositoryImpl;
import net.acdcjunior.prp.infrastructure.repository.jpa.JpaPrevisaoRepositoryImpl;
import net.acdcjunior.prp.test.FieldManipulator;
import net.acdcjunior.prp.test.InjetarEntityManagerRule;
import net.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings("unused")
public class AnoTest {
	
	@Rule
	public InjetarEntityManagerRule emRule = new InjetarEntityManagerRule();
	
	@InjetarEntityManager
	PrevisaoRepository previsaoRepository = new JpaPrevisaoRepositoryImpl();
	
	@InjetarEntityManager
	CategoriaRepository categoriaRepository = new JpaCategoriaRepositoryImpl();
	
	@InjetarEntityManager
	MovimentacaoRepository movimentacaoRepository = new JpaMovimentacaoRepositoryImpl();
	
	Ano ano;
	
//	@Before
//	public void setUp() {
//		this.ano = new AnoTabela();
//		FieldManipulator.setFieldValue(this.ano, "categoriaRepository", categoriaRepository);
//		this.ano = new AnoTabela(categoriaRepository, movimentacaoRepository, previsaoRepository);
//	}
//
//	@Test
//	public void setAno__deve_popular_ano() {
//		ano.setAno(2011);
//		List<AnoLinha> linhas = ano.getLinhas();
//		for (AnoLinha linha : linhas) {
//			assertNotNull(linha.getCategoria());
//			List<AnoCelula> celulas = linha.getCelulas();
//			for (AnoCelula celula : celulas) {
//				assertTrue(celula.getAno() != 0);
//				assertTrue(celula.getMes() != 0);
//				assertNotNull(celula.getValor());
//			}
//		}
//	}

}