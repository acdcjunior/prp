package net.acdcjunior.prp.test;

import java.sql.SQLException;

import org.junit.Test;

public class TestDataSourceTest {

	@Test
	public void getConnection__deve_nao_lancar_excecao() throws SQLException {
		new TestDataSource().getConnection();
	}

}
