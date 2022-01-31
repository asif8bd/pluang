package com.ashid.pluang.repository;

import org.springframework.data.repository.CrudRepository;

import com.ashid.pluang.model.RateLog;

public interface ExchangeRateRepo extends CrudRepository<RateLog, Long> {

}