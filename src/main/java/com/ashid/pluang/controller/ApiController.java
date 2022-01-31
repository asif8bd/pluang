package com.ashid.pluang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashid.pluang.model.CountryResult;
import com.ashid.pluang.model.RateLog;
import com.ashid.pluang.service.ApiService;

@RestController
public class ApiController {
	@Autowired
	ApiService apiService;

	@RequestMapping("/api/name/{name}")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CountryResult> getCountrydetails(@PathVariable String name) {
		return apiService.getCountrydetails(name);
	}

	@RequestMapping("/api/audit")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RateLog> audit() {
		return apiService.findAllRateLog();

	}

}