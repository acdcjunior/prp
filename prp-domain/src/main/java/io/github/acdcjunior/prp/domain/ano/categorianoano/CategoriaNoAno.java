package io.github.acdcjunior.prp.domain.ano.categorianoano;

import io.github.acdcjunior.prp.domain.categoria.Categoria;

import java.math.BigDecimal;
import java.util.List;

public class CategoriaNoAno {
	
	private int ano;
	private Categoria categoria;
	private List<CategoriaNoMes> categoriaNosMeses;
	
	CategoriaNoAno(int ano, Categoria categoria, List<CategoriaNoMes> categoriaNosMeses) {
		this.ano = ano;
		this.categoria = categoria;
		this.categoriaNosMeses = categoriaNosMeses;
	}

	public int getAno() {
		return ano;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public List<CategoriaNoMes> getCategoriaNosMeses() {
		return categoriaNosMeses;
	}
	
	public BigDecimal getValorMes(int mes) {
		return categoriaNosMeses.get(mes-1).getValor();
	}

}