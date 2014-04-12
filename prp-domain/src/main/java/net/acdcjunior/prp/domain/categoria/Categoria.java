package net.acdcjunior.prp.domain.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.acdcjunior.prp.domain.BaseEntity;

@Entity
@Table(name = "TB_CATEGORIA") 
public class Categoria extends BaseEntity {

	@Column(length = 50, unique = true)
    private String nome;
	
	@Column(length = 1)
	private String revisado;
    
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    public void setRevisado(String revisado) { this.revisado = revisado; }
    public String getRevisado() { return revisado; }

}