package net.acdcjunior.prp.test;

import java.util.HashMap;
import java.util.Map;

public class ObjectBuilder<T> {
	
	private Class<T> classe;
	private Map<String, Object> propriedades = new HashMap<>();
	
	public ObjectBuilder(Class<T> classe) {
		this.classe = classe;
	}
	
	public ObjectBuilder<T> with(String propriedade, Object valor) {
		propriedades.put(propriedade, valor);
		return this;
	}
	
	public T build() {
		try {
			
			T objetoEmConstrucao = this.classe.newInstance();
			for (String propriedade : propriedades.keySet()) {
				FieldManipulator.setFieldValue(objetoEmConstrucao, propriedade, propriedades.get(propriedade));
			}
			return objetoEmConstrucao;
			
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		} 

	}

}