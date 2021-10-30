package com.nab.order.validator;

import com.nab.common.event.OrderPlaced;
import com.nab.order.util.ProductGateway;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Lazy
public class OrderValidator implements Validator {
  private final ProductGateway productGateway;

  public OrderValidator(ProductGateway productGateway) {
    this.productGateway = productGateway;
  }

  @Override public boolean supports(Class<?> clazz) {
    return OrderPlaced.class.isAssignableFrom(clazz);
  }

  @Override public void validate(Object target, Errors errors) {
    validateRequiredFields(errors);
    if (!errors.hasErrors()) {
      OrderPlaced order = (OrderPlaced) target;
      productGateway
        .getProduct(order.productId())
        .ifPresentOrElse(
          product -> {
            if (product.quantity() < order.quantity()) {
              errors.rejectValue(
                "quantity",
                "Don't have enough available product",
                new Object[] {},
                "Don't have enough available product"
              );
            }
          },
          () -> errors.rejectValue(
            "productId",
            "Product doesn't exists",
            new Object[] {},
            "Product doesn't exists"
          )
        );
    }
  }

  private void validateRequiredFields(Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(
      errors,
      "productId",
      "Product's id is required",
      new Object[] {},
      "Product's id is required"
    );
    ValidationUtils.rejectIfEmptyOrWhitespace(
      errors,
      "customerName",
      "Customer's name is required",
      new Object[] {},
      "Customer's name is required"
    );
    ValidationUtils.rejectIfEmptyOrWhitespace(
      errors,
      "customerAddress",
      "Customer's address is required",
      new Object[] {},
      "Customer's address is required"
    );
    ValidationUtils.rejectIfEmptyOrWhitespace(
      errors,
      "quantity",
      "quantity is required",
      new Object[] {},
      "quantity is required"
    );
  }
}
