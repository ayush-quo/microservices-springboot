package com.self.currencyexchangeservice.controller;


import com.self.currencyexchangeservice.model.CurrencyExchange;
import com.self.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
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
      log.info("Request from: {} to: {}", from, to); //log statement added to check id assigned by sleuth.
      CurrencyExchange currencyExchange =  currencyExchangeRepository.findByFromAndTo(from, to)
                                                                     .orElseThrow(
                                                                       () -> new RuntimeException("Unable to find data for from: " + from + " to: " + to));
      currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
      return currencyExchange;
  }
}
