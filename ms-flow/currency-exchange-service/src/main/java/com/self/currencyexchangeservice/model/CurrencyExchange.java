package com.self.currencyexchangeservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyExchange {

  @Id
  private Long id;

  @Column(name = "currency_from")
  private String from;

  @Column(name = "currency_to")
  private String to;

  private BigDecimal conversionMultiple;

  // Below variable is just for checking from which instance response is received
  private String environment;
}
