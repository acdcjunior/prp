package io.github.acdcjunior.prp.domain.categoria;

import io.github.acdcjunior.prp.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categoria")
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