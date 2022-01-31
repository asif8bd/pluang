package com.ashid.pluang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RateLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	long id;


	@Column
	String username;

	@Column
	String timestamp;

	@Column
	String date;

	@Column
	String code;

	@Column
	String idrRate;

	public RateLog() {
		super();
	}

	public RateLog(String username, String timestamp, String date, String code, String idrRate) {
		super();
		this.username = username;
		this.timestamp = timestamp;
		this.date = date;
		this.code = code;
		this.idrRate = idrRate;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
