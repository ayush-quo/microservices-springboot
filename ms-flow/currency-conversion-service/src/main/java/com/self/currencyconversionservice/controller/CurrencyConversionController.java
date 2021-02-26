package com.self.currencyconversionservice.controller;


import java.math.BigDecimal;

import com.self.currencyconversionservice.CurrencyExchangeProxy;
import com.self.currencyconversionservice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyConversionController {

  @Autowired
  private CurrencyExchangeProxy currencyExchangeProxy;

  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
      CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
      currencyConversion.setQuantity(quantity);
      currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
      return currencyConversion;
  }
}