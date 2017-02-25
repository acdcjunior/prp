package io.github.acdcjunior.prp.legacy.test;

import org.junit.Test;

import java.sql.SQLException;

public class TestDataSourceTest {

	@Test
	public void getConnection__deve_nao_lancar_excecao() throws SQLException {
		new TestDataSource().getConnection();
	}

}
