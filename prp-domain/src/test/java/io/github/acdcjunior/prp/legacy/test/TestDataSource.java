package io.github.acdcjunior.prp.legacy.test;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.jdbcx.JdbcDataSourceFactory;

import javax.naming.Reference;
import javax.naming.StringRefAddr;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class TestDataSource extends org.h2.jdbcx.JdbcDataSource {
    
	private static final long serialVersionUID = 1L;

    public TestDataSource() {
        super();
        super.setURL(TestConstants.URL_H2_EM_MEMORIA);
        super.setUser("sa");
        super.setPassword("");
    }

    /**
     * Apenas sobrescrevendo o metodo da JdbcDataSource, trocando a linha:
     * 
     * Reference ref = new Reference(getClass()          .getName(), factoryClassName, null);
     * para:
     * Reference ref = new Reference(JdbcDataSource.class.getName(), factoryClassName, null);
     * 
     * Se este metodo nao eh sobrescrito, o getClass() retorna TestDataSource, o que, nao me
     * pergunte o porquê, dah pau no JNDI.
     */
    @Override
    public Reference getReference() {
	    String factoryClassName = JdbcDataSourceFactory.class.getName();
	    Reference ref = new Reference(JdbcDataSource.class.getName(), factoryClassName, null);
	    ref.add(new StringRefAddr("url", super.getURL()));
	    ref.add(new StringRefAddr("user", super.getUser()));
	    ref.add(new StringRefAddr("password", super.getPassword()));
	    ref.add(new StringRefAddr("loginTimeout", String.valueOf(super.getLoginTimeout())));
	    ref.add(new StringRefAddr("description", super.getDescription()));
	    return ref;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

}