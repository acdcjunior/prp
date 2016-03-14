package io.github.acdcjunior.prp.arquivo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.acdcjunior.prp.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "tb_arquivo")
public class Arquivo extends BaseEntity {

    @Column(length = 50)
    private String nome;

    @Column
    private String descricao;

    @Column(name="DATA_REFERENCIA")
    private Date dataReferencia;

    @Column(name="NOME_ARQUIVO", length=100)
    private String nomeArquivo;

    @Column
    @Lob
    @JsonIgnore
    private Blob conteudo;

    @Column(name="TIPO_CONTEUDO")
    private String tipoConteudo;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Date getDataReferencia() { return dataReferencia; }
    public void setDataReferencia(Date dataReferencia) { this.dataReferencia = dataReferencia; }

    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }

    public Blob getConteudo() { return conteudo; }
    public void setConteudo(Blob conteudo) { this.conteudo = conteudo; }

    public String getTipoConteudo() { return tipoConteudo; }
    public void setTipoConteudo(String tipoConteudo) { this.tipoConteudo = tipoConteudo; }

}