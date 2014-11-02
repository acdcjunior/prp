package net.acdcjunior.prp.domain.movimentacao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import net.acdcjunior.prp.domain.BaseEntity;
import net.acdcjunior.prp.domain.previsao.Previsao;

@Entity
@Table(name = "TB_Movimentacao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movimentacao extends BaseEntity {

	@Column
    private Date data;
    
	@Column
	private String numeroDocumento;
	
	@Column
	private String descricao1;
	
	@Column
	private String descricao2;
	
	@Column
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name="ORIGEM")
	private Origem origem;
	
	@Column
	private BigDecimal saldo;
	
	@ManyToOne
	@JoinColumn(name="REALIZA")
	private Previsao realiza;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="anterior")
	private Movimentacao anterior;
	
	@JsonIgnore
	@OneToMany(mappedBy="anterior", fetch=FetchType.LAZY)
    private List<Movimentacao> seguinte;

	@Column(name="anterior", insertable=false, updatable=false)
	private Integer anteriorId;
	
	public Date getData() { return data; }
	public void setData(Date data) { this.data = data; }

	public String getNumeroDocumento() { return numeroDocumento; }
	public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
	
	public String getDescricao1() { return descricao1; }
	public void setDescricao1(String descricao1) { this.descricao1 = descricao1; }
	
	public String getDescricao2() { return descricao2; }
	public void setDescricao2(String descricao2) { this.descricao2 = descricao2; }
	
	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }
	
	public Origem getOrigem() { return origem; }
	public void setOrigem(Origem origem) { this.origem = origem; }

	public BigDecimal getSaldo() { return saldo; }
	public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
	
	public Previsao getRealiza() { return realiza; }
	public void setRealiza(Previsao realiza) { this.realiza = realiza; }
	
	public Movimentacao getAnterior() {	return anterior; }
	public void setAnterior(Movimentacao anterior) { this.anterior = anterior; }
	
	public Movimentacao getSeguinte() { return seguinte.isEmpty() ? null : seguinte.get(0); }
//	public void setSeguinte(Movimentacao seguinte) { this.seguinte.clear(); this.seguinte.add(seguinte); }
	
	public Integer getAnteriorId() { return anteriorId; }
	
	public boolean isNotInMonth(int ano, int mes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.data);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return year != ano || month != mes;
	}
	
}