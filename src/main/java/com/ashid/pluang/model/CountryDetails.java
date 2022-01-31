package com.ashid.pluang.model;

import java.util.Map;

public class CountryDetails {
	Map<String, Object> name;
	Map<String, Map<String, String>> currencies;
	String population;

	public Map<String, Object> getName() {
		return name;
	}

	public void setName(Map<String, Object> name) {
		this.name = name;
	}

	public Map<String, Map<String, String>> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, Map<String, String>> currencies) {
		this.currencies = currencies;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
}
