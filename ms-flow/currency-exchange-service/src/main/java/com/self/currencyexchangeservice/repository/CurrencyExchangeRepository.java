package com.self.currencyexchangeservice.repository;


import java.util.Optional;

import com.self.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

  Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
