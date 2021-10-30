package com.nab.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nab.common.event.OrderPlaced;
import com.nab.order.event.OrderEventPublisher;
import com.nab.order.validator.OrderValidator;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  private final OrderValidator orderValidator;

  private final OrderEventPublisher orderEventPublisher;

  public OrderController(
    OrderValidator orderValidator,
    OrderEventPublisher orderEventPublisher
  ) {
    this.orderValidator = orderValidator;
    this.orderEventPublisher = orderEventPublisher;
  }

  @InitBinder()
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(orderValidator);
  }

  @PostMapping("/orders")
  public ResponseEntity<?> createOrder(
    @RequestBody @Validated OrderPlaced order,
    BindingResult bindingResult
  ) throws JsonProcessingException {
    if (bindingResult.hasErrors()) throw new RepositoryConstraintViolationException(bindingResult);
    orderEventPublisher.placeOrder(order);
    return ResponseEntity.ok().build();
  }
}
