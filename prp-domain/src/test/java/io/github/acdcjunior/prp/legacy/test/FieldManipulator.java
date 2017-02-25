package io.github.acdcjunior.prp.legacy.test;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

import java.lang.reflect.Field;

public class FieldManipulator {
	
	/**
	 * Encontra o campo na classe ou em seus pais.
	 */
	public static void setFieldValue(final Object instanciaASetar,
									final String nomeCampo,
									final Object valor) {
		
		Class<?> clazz = instanciaASetar.getClass();
				
		ReflectionUtils.doWithFields(clazz,
			new FieldCallback() {
				@Override
				public void doWith(Field f) throws IllegalArgumentException, IllegalAccessException {
					setFieldValue(f, instanciaASetar, valor);
				}
			}, new FieldFilter() {
				@Override public boolean matches(Field field) {
					return field.getName().equals(nomeCampo);
				}
			}
		);
	}
	
	public static void setFieldValue(Field f, Object instancia, Object valor) {
		try {
			boolean acessibilidadeAnterior = f.isAccessible();
			f.setAccessible(true);
			f.set(instancia, valor);
			f.setAccessible(acessibilidadeAnterior);
		} catch (IllegalAccessException e) { throw new RuntimeException(e); }
	}
	
	public static Object getFieldValue(Field f, Object instancia) {
		try {
			boolean acessibilidadeAnterior = f.isAccessible();
			f.setAccessible(true);
			Object fieldValue = f.get(instancia);
			f.setAccessible(acessibilidadeAnterior);
			return fieldValue;
		} catch (IllegalAccessException e) { throw new RuntimeException(e); }
		
	}

}