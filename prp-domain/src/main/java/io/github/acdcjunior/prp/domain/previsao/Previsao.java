package io.github.acdcjunior.prp.domain.previsao;

import io.github.acdcjunior.prp.domain.BaseEntity;
import io.github.acdcjunior.prp.domain.categoria.Categoria;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_previsao")
@NamedQueries({
		@NamedQuery(name = "Previsao.findByCategoria", query = "SELECT p FROM Previsao p WHERE p.categoria = :categoria"),
		@NamedQuery(name = "Previsao.findByAnoMesCategoria", query = "SELECT p FROM Previsao p WHERE YEAR(p.data) = :ano and MONTH(p.data) = :mes AND p.categoria = :categoria"),
		@NamedQuery(name = "Previsao.findByAnoMes", query = "SELECT p FROM Previsao p WHERE YEAR(p.data) = :ano and MONTH(p.data) = :mes"),
		@NamedQuery(name = "Previsao.findByAno", query = "SELECT p FROM Previsao p WHERE YEAR(p.data) = :ano"),
		@NamedQuery(name = "Previsao.sumValorByAnoMesCategoria", query = "SELECT sum(p.valor) FROM Previsao p WHERE YEAR(p.data) = :ano and MONTH(p.data) = :mes AND p.categoria = :categoria")
})
public class Previsao extends BaseEntity {
	
	@Column
	private LocalDate data;

	@Column(length=255)
    private String descricao;
    
	@Column
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name="CATEGORIA")
	private Categoria categoria;
	
	@Column
	private boolean realizada;
	
	@Column
	private boolean bill;
	
	public LocalDate getData() { return data; }
	public void setData(LocalDate data) { this.data = data; }
	
	public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getDescricao() { return descricao; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    
	public Categoria getCategoria() { return categoria; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	
	public boolean isRealizada() { return realizada; }
	public void setRealizada(boolean realizada) { this.realizada = realizada; }
	
	public boolean isBill() { return bill; }
	public void setBill(boolean bill) { this.bill = bill; }
	
	public int getAno() {
		return data.getYear();
	}

	public int getMes() {
		return data.getMonthValue();
	}
	
}