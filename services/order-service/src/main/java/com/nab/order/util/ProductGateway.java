package com.nab.order.util;

import com.nab.order.model.ProductDto;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductGateway {
  private final String productServiceUri;

  public ProductGateway(@Value("${product-service-uri}") String productServiceUri) {
    this.productServiceUri = productServiceUri;
  }

  public Optional<ProductDto> getProduct(Long productId) {
    RestTemplate restTemplate = new RestTemplate();

    try {
      ResponseEntity<ProductDto> response =
        restTemplate.exchange(
          RequestEntity.get(URI.create(productServiceUri + "/products/" + productId)).build(),
          ProductDto.class
        );

      if (response.getBody() == null
        || !response.hasBody()
        || !response.getStatusCode().is2xxSuccessful()) {
        return Optional.empty();
      }

      return Optional.of(response.getBody());
    } catch (HttpClientErrorException.NotFound ex) {
      return Optional.empty();
    }
  }
}
