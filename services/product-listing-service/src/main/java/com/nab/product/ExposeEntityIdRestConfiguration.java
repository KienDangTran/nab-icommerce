package com.nab.product;

import com.nab.product.model.Product;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class ExposeEntityIdRestConfiguration implements RepositoryRestConfigurer {
  @Override public void configureRepositoryRestConfiguration(
    RepositoryRestConfiguration config, CorsRegistry cors
  ) {
    config.exposeIdsFor(Product.class);
    RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
  }
}