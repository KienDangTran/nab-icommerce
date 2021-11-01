package com.nab.product.controller;

import com.nab.product.model.Product;
import com.nab.product.repository.ProductRepository;
import com.nab.product.specification.ProductSpecification;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestController
public class ProductController {
  private final PagedResourcesAssembler<Product> pagedResourcesAssembler;

  private final ProductRepository productRepository;

  public ProductController(
    PagedResourcesAssembler<Product> pagedResourcesAssembler,
    ProductRepository productRepository
  ) {
    this.pagedResourcesAssembler = pagedResourcesAssembler;
    this.productRepository = productRepository;
  }

  @GetMapping("/products")
  public ResponseEntity<?> filterProducts(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String branch,
    @RequestParam(required = false) String colour,
    @RequestParam(required = false) BigDecimal price_gte,
    @RequestParam(required = false) BigDecimal price_lte,
    @PageableDefault Pageable pageable
  ) {
    Page<Product> products = productRepository.findAll(
      ProductSpecification.createProductSpecification(
        name,
        branch,
        colour,
        price_gte,
        price_lte
      ),
      pageable
    );

    return ResponseEntity.ok(pagedResourcesAssembler.toModel(products));
  }
}
