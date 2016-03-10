package io.github.acdcjunior.prp.domain.previsao;

import io.github.acdcjunior.prp.domain.BaseRepository;
import io.github.acdcjunior.prp.domain.categoria.Categoria;

import java.math.BigDecimal;
import java.util.List;

public interface PrevisaoRepository extends BaseRepository<Previsao> {
	
	List<Previsao> findByCategoria(Categoria categoria);
	
	List<Previsao> findByAnoMesCategoria(int ano, int mes, Categoria categoria);
	
	BigDecimal sumByAnoMesCategoria(int ano, int mes, Categoria categoria);

	List<Previsao> findByAnoMes(int ano, int mes);

	List<Previsao> findByAno(int ano);
	
}