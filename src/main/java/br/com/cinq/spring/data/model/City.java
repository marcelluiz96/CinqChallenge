package br.com.cinq.spring.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * City entity. Some comments below are for correction purposes only
 * @author marcel.mendonca
 *
 */
@Entity
@Table(name="City")
public class City implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1616718006549750218L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Identity due to MYSQL's Auto-increment
	@Column(name="id")
	private int id;
	
	@Column(nullable=false, length=40)
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	
	/** HashCode & Equals **/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * Equals is using ID as its this entity's primary key
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (id != other.id)
			return false;
		return true;
	}


	/** GETTERS & SETTERS **/


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	

}
