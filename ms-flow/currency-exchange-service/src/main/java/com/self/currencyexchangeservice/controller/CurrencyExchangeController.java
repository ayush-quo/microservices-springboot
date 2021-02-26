package com.self.currencyexchangeservice.controller;


import com.self.currencyexchangeservice.model.CurrencyExchange;
import com.self.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {

  @Autowired
  private CurrencyExchangeRepository currencyExchangeRepository;

  @Autowired
  private Environment environment;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(
    @PathVariable String from,
    @PathVariable String to
  ) {
      CurrencyExchange currencyExchange =  currencyExchangeRepository.findByFromAndTo(from, to)
                                                                     .orElseThrow(
                                                                       () -> new RuntimeException("Unable to find data for from: " + from + " to: " + to));
      currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
      return currencyExchange;
  }
}
