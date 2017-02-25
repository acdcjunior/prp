package io.github.acdcjunior.prp.legacy.test;

public interface TestConstants {

	String CAMINHO_XML_APP_CONTEXT = "file:src/main/webapp/WEB-INF/prp-servlet.xml";
	
	String URL_H2_EM_MEMORIA = "jdbc:h2:mem:bancoDeTestesEmMemoria;INIT=RUNSCRIPT FROM 'classpath:sql/esquema.sql'\\;RUNSCRIPT FROM 'classpath:sql/dados.sql'\\;";

	String NOME_CAMPO_ENTITYMANAGER = "em";

	String NOME_PERSISTENCE_UNIT = "prpPersistenceUnit";

	String URL_DATA_SOURCE = "java:/comp/env/jdbc/prpDataSource";

}