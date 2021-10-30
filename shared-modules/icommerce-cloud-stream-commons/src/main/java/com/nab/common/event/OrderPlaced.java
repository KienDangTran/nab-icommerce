package com.nab.common.event;

public record OrderPlaced(
  String customerName,
  String customerAddress,
  Long productId,
  Integer quantity
) {
  public String type() {
    return this.getClass().getSimpleName();
  }
}
