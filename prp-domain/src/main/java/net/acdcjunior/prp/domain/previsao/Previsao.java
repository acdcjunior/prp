package net.acdcjunior.prp.domain.previsao;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.acdcjunior.prp.domain.BaseEntity;
import net.acdcjunior.prp.domain.categoria.Categoria;

import org.joda.time.DateTime;

@Entity
@Table(name = "TB_PREVISAO") 
public class Previsao extends BaseEntity {
	
	@Column
	private Date data;

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
	
	public Date getData() { return data; }
	public void setData(Date data) { this.data = data; }
	
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
		DateTime data = new DateTime(this.data);
		return data.getYear();
	}

	public int getMes() {
		DateTime data = new DateTime(this.data);
		return data.getMonthOfYear();
	}
	
}