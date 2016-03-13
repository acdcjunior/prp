package io.github.acdcjunior.prp.domain.movimentacao;

import io.github.acdcjunior.prp.domain.BaseEntity;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_movimentacao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQueries({
        @NamedQuery(name = "Movimentacao.findByDescricao1", query = "SELECT m FROM  Movimentacao m WHERE m.descricao1 = :descricao1"),
        @NamedQuery(name = "Movimentacao.findByRealiza", query = "SELECT m FROM  Movimentacao m WHERE m.realiza = :previsao"),
        @NamedQuery(name = "Movimentacao.sumValorByRealiza", query = "SELECT sum(m.valor) FROM  Movimentacao m WHERE m.realiza = :previsao"),
        @NamedQuery(name = "Movimentacao.findByPrevisaoAnoMesCategoria", query = "SELECT m FROM Movimentacao m WHERE m.realiza IN (SELECT p FROM Previsao p WHERE YEAR(p.data) = :ano AND MONTH(p.data) = :mes AND p.categoria = :categoria)"),
        @NamedQuery(name = "Movimentacao.findByAnoMesSemCategoria", query = "SELECT m FROM Movimentacao m WHERE YEAR(m.data) = :ano AND MONTH(m.data) = :mes AND m.realiza IS NULL"),
        @NamedQuery(name = "Movimentacao.sumValorByPrevisaoAnoMesCategoria", query = "SELECT sum(m.valor) FROM Movimentacao m WHERE m.realiza IN (SELECT p FROM Previsao p WHERE YEAR(p.data) = :ano AND MONTH(p.data) = :mes AND p.categoria = :categoria )"),
        @NamedQuery(name = "Movimentacao.sumValorByAnoMesSemRealizacao", query = "SELECT sum(m.valor) FROM Movimentacao m WHERE YEAR(m.data) = :ano AND MONTH(m.data) = :mes AND m.realiza is null"),
        @NamedQuery(name = "Movimentacao.countByAnoMesSemRealizacao", query = "SELECT count(m) FROM Movimentacao m WHERE YEAR(m.data) = :ano AND MONTH(m.data) = :mes AND m.realiza is null"),
        @NamedQuery(name = "Movimentacao.findPrimeiraMovimentacaoDoMes", query = "SELECT m FROM Movimentacao m WHERE YEAR(m.data) = :ano AND MONTH(m.data) = :mes AND (YEAR(m.anterior.data) != :ano OR MONTH(m.anterior.data) != :mes)"),
        @NamedQuery(name = "Movimentacao.findUltimaMovimentacaoDoMes", query = "SELECT m.anterior FROM Movimentacao m WHERE (YEAR(m.data) != :ano OR MONTH(m.data) != :mes) AND (YEAR(m.anterior.data) = :ano AND MONTH(m.anterior.data) = :mes)"),
        @NamedQuery(name = "Movimentacao.findByAno", query = "SELECT m FROM Movimentacao m WHERE YEAR(m.data) = :ano"),
        @NamedQuery(name = "Movimentacao.findByAnoMes", query = "SELECT m FROM Movimentacao m WHERE YEAR(m.data) = :ano AND MONTH(m.data) = :mes"),
        @NamedQuery(name = "Movimentacao.findUltimaMovimentacaoByOrigem", query = "SELECT m FROM Movimentacao m WHERE m.origem = :origem AND m.id not in (SELECT m2.anterior FROM Movimentacao m2 WHERE m2.origem = :origem)"),
        @NamedQuery(name = "Movimentacao.findAnos", query = "SELECT DISTINCT YEAR(m.data) FROM Movimentacao m ORDER BY YEAR(m.data)")
})
public class Movimentacao extends BaseEntity {

	@Column
    private LocalDate data;

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

	public LocalDate getData() { return data; }
	public void setData(LocalDate data) { this.data = data; }

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
		int year = this.data.getYear();
		int month = this.data.getMonthValue();
		return year != ano || month != mes;
	}

}