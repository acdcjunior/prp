package io.github.acdcjunior.prp.extratos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Origem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String alias;

	@Column(nullable = false)
	private String nome;

	protected Origem() {
		// exigido pelo hibernate
	}

	public String getAlias() {
		return alias;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Origem #" + Objects.toString(id, "??") + "," + getAlias() + "," + getNome();
	}

}
