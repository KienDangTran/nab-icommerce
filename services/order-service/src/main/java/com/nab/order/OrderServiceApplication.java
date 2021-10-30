package com.nab.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.nab.order", "com.nab.common"})
@EntityScan({"com.nab.order"})
@EnableJpaRepositories({"com.nab.order"})
@EnableScheduling
public class OrderServiceApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(OrderServiceApplication.class)
      .properties("spring.config.additional-location:"
        + "classpath:application-rest.yml"
        + ",classpath:application-cloud-stream.yml")
      .build().run(args);
    //SpringApplication.run(OrderServiceApplication.class, args);
  }
}
