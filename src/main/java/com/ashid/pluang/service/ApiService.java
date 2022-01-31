package com.ashid.pluang.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ashid.pluang.model.CountryDetails;
import com.ashid.pluang.model.CountryResult;
import com.ashid.pluang.model.LatestRate;
import com.ashid.pluang.model.RateLog;
import com.ashid.pluang.repository.ExchangeRateRepo;

@Service
public class ApiService {
	@Value("${restcountries.api.url}")
	private String countryApiUrl;

	@Value("${fixer.api.url}")
	private String fixerApiUrl;

	@Value("${fixer.api.access_key}")
	private String fixerApiAccessKey;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ExchangeRateRepo rateRepo;

	public List<CountryResult> getCountrydetails(String param) {
		String url = countryApiUrl + param + "?fields=name,population,currencies";
		CountryDetails[] cdList= new CountryDetails[1];
		try {
			cdList= restTemplate.getForObject(url, CountryDetails[].class);
		} catch (Exception e) {
			return new ArrayList<CountryResult>();
		}
		
		List<CountryResult> result = new ArrayList<CountryResult>();

		for (CountryDetails countryDetails : cdList) {

			CountryResult countryResult = new CountryResult();
			Map<String, Object> name = countryDetails.getName();
			String official = (String) name.get("official");
			String population = countryDetails.getPopulation();
			countryResult.setFullName(official);
			countryResult.setPopulation(population);

			Map<String, Map<String, String>> currencies = countryDetails.getCurrencies();
			List<Map<String, String>> currencyList = parseCurrency(currencies);
			countryResult.setCurrencies(currencyList);
			result.add(countryResult);

			// Only Return the first country if restcountries.com returns multiple country.
			break;

		}
		return result;

	}

	private List<Map<String, String>> parseCurrency(Map<String, Map<String, String>> currencies) {
		
		List<Map<String, String>> currencyList = new ArrayList<>();
		for (String code : currencies.keySet()) {
			Map<String, String> currencyMap = new HashMap<>();
			String currencyName = currencies.get(code).get("name");
			String currencySymbol = currencies.get(code).get("symbol");
			currencyMap.put("code", code);
			currencyMap.put("name", currencyName);
			currencyMap.put("symbol", currencySymbol);
			currencyMap.put("IDR-rate", getIdrRate(code));
			currencyList.add(currencyMap);
		}
		return currencyList;
	}

	String getIdrRate(String code) {
		// Get exchange rate
		String exchangeURL = fixerApiUrl + "?access_key=" + fixerApiAccessKey + "&symbols=" + code + ",IDR";
		LatestRate xRate = restTemplate.getForObject(exchangeURL, LatestRate.class);
		if (xRate == null ) {
			return "";
		}else if(xRate.getRates()==null) {
			return "";
		}
		Map<String, String> rates = xRate.getRates();
		String codeRate = rates.get(code);
		String idrRate = rates.get("IDR");

		BigDecimal a = new BigDecimal(codeRate);
		BigDecimal b = new BigDecimal(idrRate);
		BigDecimal c = b.divide(a, 6, BigDecimal.ROUND_HALF_UP);
		String result = c.toString();

		// Save the exchage rate for audit purpose
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		RateLog ratelog = new RateLog(username, xRate.getTimestamp(), xRate.getDate(), code, result);
		rateRepo.save(ratelog);

		return result;
	}

	public List<RateLog> findAllRateLog() {
		return (List<RateLog>) rateRepo.findAll();
	}

}
