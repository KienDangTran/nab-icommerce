package com.nab.order.repository;

import com.nab.order.model.OrderEvent;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

public interface OrderEventRepository extends CrudRepository<OrderEvent, Long> {
  Stream<OrderEvent> findAllBySentIsFalseOrderByEventTimestamp();
}
