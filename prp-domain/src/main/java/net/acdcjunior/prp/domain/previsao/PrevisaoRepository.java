package net.acdcjunior.prp.domain.previsao;

import java.math.BigDecimal;
import java.util.List;

import net.acdcjunior.prp.domain.BaseRepository;
import net.acdcjunior.prp.domain.categoria.Categoria;

public interface PrevisaoRepository extends BaseRepository<Previsao> {
	
	List<Previsao> findByCategoria(Categoria categoria);
	
	List<Previsao> findByAnoMesCategoria(int ano, int mes, Categoria categoria);
	
	BigDecimal sumByAnoMesCategoria(int ano, int mes, Categoria categoria);

	List<Previsao> findByAnoMes(int ano, int mes);
	
}