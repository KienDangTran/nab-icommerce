package com.nab.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.nab.product", "com.nab.common"})
@EntityScan({"com.nab.product"})
@EnableJpaRepositories({"com.nab.product"})
public class ProductServiceApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(ProductServiceApplication.class)
      .properties("spring.config.additional-location:"
        + "classpath:application-rest.yml"
        + ",classpath:application-cloud-stream.yml")
      .build().run(args);
  }
}
