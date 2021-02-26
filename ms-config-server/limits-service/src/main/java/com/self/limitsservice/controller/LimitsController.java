package com.self.limitsservice.controller;


import com.self.limitsservice.model.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LimitsController {

  @Autowired
  LimitConfiguration limitConfiguration;

  @GetMapping("/limits")
  public LimitConfiguration retrieveLimitsFromConfigurations() {
    return new LimitConfiguration(limitConfiguration.getMaximum(), limitConfiguration.getMinimum());
  }
}
