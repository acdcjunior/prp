package net.acdcjunior.prp.domain.ano;

import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import net.acdcjunior.prp.infrastructure.jpa.repository.JpaCategoriaRepositoryImpl;
import net.acdcjunior.prp.infrastructure.jpa.repository.JpaMovimentacaoRepositoryImpl;
import net.acdcjunior.prp.infrastructure.jpa.repository.JpaPrevisaoRepositoryImpl;
import net.acdcjunior.prp.test.InjetarEntityManagerRule;
import net.acdcjunior.prp.test.InjetarEntityManagerRule.InjetarEntityManager;

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