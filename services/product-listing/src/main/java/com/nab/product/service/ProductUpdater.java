//package com.nab.product.service;
//
//import com.nab.product.model.Product;
//import com.nab.product.repository.ProductRepository;
//import com.xyz.shared.domainevent.OrderPlaced;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductUpdater {
//  private final ProductRepository productRepository;
//
//  public ProductUpdater(ProductRepository productRepository) {
//    this.productRepository = productRepository;
//  }
//
//  @StreamListener("order-channel")
//  public void listenOrderEvent(@Payload OrderPlaced order) {
//    productRepository
//        .findById(order.getProductId())
//        .ifPresent(product -> updateProductFromOrder(product, order));
//  }
//
//  private void updateProductFromOrder(Product product, OrderPlaced order) {
//    product.setQuantity(product.getQuantity() - order.getQuantity());
//    productRepository.save(product);
//  }
//}
