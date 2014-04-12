package net.acdcjunior.prp.domain.movimentacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.acdcjunior.prp.domain.BaseEntity;

@Entity
@Table(name = "TB_ORIGEM") 
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