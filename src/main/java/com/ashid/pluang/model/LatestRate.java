package com.ashid.pluang.model;

import java.util.Map;

public class LatestRate {
	String success;
	String timestamp;
	String base;
	String date;
	Map<String, String> rates;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, String> getRates() {
		return rates;
	}

	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}

}
