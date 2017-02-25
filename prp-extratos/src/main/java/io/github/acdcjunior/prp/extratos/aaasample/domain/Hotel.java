package io.github.acdcjunior.prp.extratos.aaasample.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	@NaturalId
	private City city;

	@Column(nullable = false)
	@NaturalId
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zip;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	private Set<Review> reviews;

	protected Hotel() {
	}

	public Hotel(City city, String name) {
		this.city = city;
		this.name = name;
	}

	public City getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getZip() {
		return this.zip;
	}
}
