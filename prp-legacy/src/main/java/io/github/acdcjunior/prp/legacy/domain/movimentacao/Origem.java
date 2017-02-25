package io.github.acdcjunior.prp.legacy.domain.movimentacao;

import io.github.acdcjunior.prp.legacy.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_origem")
public class Origem extends BaseEntity {

	@Column(length = 100, unique = true)
    private String nome;
	
	@Column(length = 50, unique = true)
	private String alias;
    
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    public void setAlias(String alias) { this.alias = alias; }
    public String getAlias() { return alias; }

}