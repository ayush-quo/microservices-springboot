package com.self.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiGatewayConfiguration {

  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder
             .routes()
             .route(route -> route.path("/get")
                                  .filters(f -> f.addRequestHeader("myHeader", "headerValue")
                                                 .addRequestParameter("myParam", "paramValue"))
                                  .uri("http://httpbin.org:80"))
             .route(route -> route.path("/currency-exchange/**")
                                  .uri("lb://currency-exchange-service"))
             .route(route -> route.path("/currency-conversion/**")
                                  .uri("lb://currency-conversion-service"))
             .build();
  }
}
