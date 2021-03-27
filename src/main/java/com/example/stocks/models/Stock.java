package com.example.stocks.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Stock {

	@Id
	@Column(unique = true)
	private String name;

	private double[] quotes;

	public Stock() {
	}

	public Stock(String name, List<Float> quotes) {
		super();
		this.name = name;
		this.quotes = quotes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[] getQuotes() {
		return quotes;
	}
	
	public void setQuotes(double[] quotes) {
		this.quotes = quotes;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
