package com.nab.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.nab.product"})
@EnableJpaRepositories({"com.nab.product"})
public class ProductServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class);
    //new SpringApplicationBuilder(ProductServiceApplication.class)
    //    .properties("spring.config.additional-location:"
    //        + "classpath:application-rest.yml,"
    //        + "classpath:application-cloud-stream.yml")
    //    .build().run(args);
  }
}
