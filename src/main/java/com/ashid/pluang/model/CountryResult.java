package com.ashid.pluang.model;

import java.util.List;
import java.util.Map;

public class CountryResult {
	String fullName;
	String population;
	List<Map<String, String>> currencies;

	public CountryResult() {
		super();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public List<Map<String, String>> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Map<String, String>> currencies) {
		this.currencies = currencies;
	}

}
