package com.self.currencyexchangeservice.controller;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@Slf4j
public class CircuitBreakerController {

  @GetMapping("/currency-exchange/sample-api")
//  @Retry(name = "default") By default 3 retries happen.
  @Retry(name = "sample-api", fallbackMethod = "fallbackSampleAPI")
  public String sampleAPI() {
    log.info("Request for Sample API received!");
    return new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class).getBody();
  }


  /**
   * For @CircuitBreaker Run "watch -n 0.1 curl http://localhost:8001/currency-exchange/sample-api-circuit-breaker" in console and observe log
   * pattern with timings.
   * For more read notes.
   * @return
   */
  @GetMapping("/currency-exchange/sample-api-circuit-breaker")
  @CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackSampleAPI")
//  @RateLimiter(name = "sample-api", fallbackMethod = "fallbackSampleAPI")
  public String sampleCBAPI() {
    log.info("Request for Sample Circuit Breaker API received!");
    return new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class).getBody();
  }

  @GetMapping("/currency-exchange/sample-api-rate-limiter")
  @RateLimiter(name = "sample-api", fallbackMethod = "fallbackSampleAPI")
  public String sampleRLAPI() {
    log.info("Request for Sample Rate Limiter API received!");
    return "Sample Rate Limiter API Response";
  }

  @GetMapping("/currency-exchange/sample-api-bulkhead")
  @Bulkhead(name = "sample-api", fallbackMethod = "fallbackSampleAPI")
  public String sampleBulkheadAPI() throws InterruptedException {
    log.info("Request for Sample bulkhead API received!");
    Thread.sleep(3000);
    return "Sample BulkHead API Response";
  }

  /**
   * This fallback method should always be public. Also, argument for Exception is needed else it throws UndeclaredThrowableException.
   * This Exception is same for what main API method throws, so we can have multiple fallback methods for a single API as per what exception it can
   * throw.
   * @param ex
   * @return string
   */
  public String fallbackSampleAPI(Exception ex) {
    log.info("Fallback");
    return "Fallback Sample String";
  }
}
