package io.github.acdcjunior.prp.legacy.test;

import org.apache.naming.java.javaURLContextFactory;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class InjetarEntityManagerRule implements MethodRule {
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface InjetarEntityManager { }
	
	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManager em;
	
	public InjetarEntityManagerRule() {
		inicializarEntityManagerFactory();
	}
	
	public EntityManager getEntityManagerInjetado() {
		return em;
	}
	
	public void begin() {
		getEntityManagerInjetado().getTransaction().begin();
	}
	
	public void commit() {
		getEntityManagerInjetado().getTransaction().commit();
	}
	
	@Override
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		this.em = entityManagerFactory.createEntityManager();
		InjetorDeEntityManager.injetarCamposAnotados(target, TestConstants.NOME_CAMPO_ENTITYMANAGER, this.em);
		return base;
	}
	
	private void inicializarEntityManagerFactory() {
		try {
			if (entityManagerFactory == null) {
				realizarBindJndiDoDataSourceDeTestes();
				entityManagerFactory = Persistence.createEntityManagerFactory(TestConstants.NOME_PERSISTENCE_UNIT);
				desfazerBindJndiDoDataSourceDeTestes();
			}
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	private static void realizarBindJndiDoDataSourceDeTestes() throws NamingException {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		
		InitialContext ic = new InitialContext();
		ic.createSubcontext("java:");
		ic.createSubcontext("java:/comp");
		ic.createSubcontext("java:/comp/env");
		ic.createSubcontext("java:/comp/env/jdbc");
		
		ic.bind(TestConstants.URL_DATA_SOURCE, criarDS());
	}

	private static JdbcDataSource criarDS() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(TestConstants.URL_H2_EM_MEMORIA);
		ds.setUser("sa");
		ds.setPassword("");
		return ds;
	}
	
	private static void desfazerBindJndiDoDataSourceDeTestes() throws NamingException {
		InitialContext ic = new InitialContext();
		ic.unbind(TestConstants.URL_DATA_SOURCE);
	}

}

class InjetorDeEntityManager {
	
	public static void injetarCamposAnotados(Object instanciaDaClasseDeTestes,
											 String nomeCampo,
											 EntityManager entityManagerAInjetar) {
		
		Class<?> clazz = instanciaDaClasseDeTestes.getClass();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(InjetarEntityManagerRule.InjetarEntityManager.class)) {
				injetar(instanciaDaClasseDeTestes, f, nomeCampo, entityManagerAInjetar);
			}
		}
		
	}

	private static void injetar(final Object instanciaDaClasseDeTestes,
								final Field f,
								final String nomeCampo,
								final EntityManager entityManagerAInjetar) {
		
		final Object instanciaDaClasseSobTestes = FieldManipulator.getFieldValue(f, instanciaDaClasseDeTestes);
		Class<?> classeSobTestes = instanciaDaClasseSobTestes.getClass();
			
		ReflectionUtils.doWithFields(classeSobTestes,
			new FieldCallback() {
				@Override
				public void doWith(Field f) throws IllegalArgumentException, IllegalAccessException {
					FieldManipulator.setFieldValue(f, instanciaDaClasseSobTestes, entityManagerAInjetar);
				}
			}, new FieldFilter() {
				@Override public boolean matches(Field field) {
					boolean campoNaoEhStatic = !Modifier.isStatic(field.getModifiers());
					boolean campoTemNomeEsperado = field.getName().equals(nomeCampo);
					return campoNaoEhStatic && campoTemNomeEsperado;
				}
			}
		);
	}

}