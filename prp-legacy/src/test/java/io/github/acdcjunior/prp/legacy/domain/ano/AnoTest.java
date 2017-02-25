package io.github.acdcjunior.prp.legacy.domain.ano;

import io.github.acdcjunior.prp.legacy.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.legacy.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.legacy.domain.previsao.PrevisaoRepository;
import io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository.JpaCategoriaRepositoryImpl;
import io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository.JpaMovimentacaoRepositoryImpl;
import io.github.acdcjunior.prp.legacy.infrastructure.jpa.repository.JpaPrevisaoRepositoryImpl;
import io.github.acdcjunior.prp.legacy.test.InjetarEntityManagerRule;
import io.github.acdcjunior.prp.legacy.test.InjetarEntityManagerRule.InjetarEntityManager;
import org.junit.Rule;

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