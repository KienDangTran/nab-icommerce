package com.nab.order.model;

import java.math.BigDecimal;

public record ProductDto(
  Long id,
  String name,
  String branch,
  BigDecimal price,
  Integer quantity,
  String colour
) {
}
