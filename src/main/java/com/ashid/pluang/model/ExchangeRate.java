package com.ashid.pluang.model;

public class ExchangeRate {

	String username;
	String timestamp;
	String date;
	String code;
	String idrRate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdrRate() {
		return idrRate;
	}

	public void setIdrRate(String idrRate) {
		this.idrRate = idrRate;
	}

	@Override
	public String toString() {
		return "ExchangeRate [username=" + username + ", timestamp=" + timestamp + ", date=" + date + ", code=" + code
				+ ", idrRate=" + idrRate + "]";
	}

}
