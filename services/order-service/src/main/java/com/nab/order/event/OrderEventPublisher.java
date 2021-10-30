package com.nab.order.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.common.event.OrderPlaced;
import com.nab.order.model.OrderEvent;
import com.nab.order.repository.OrderEventRepository;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class OrderEventPublisher {
  private final OrderEventRepository orderEventRepository;

  private final ObjectMapper objectMapper;

  private final EventStreamConfig.OrderEventStream source;

  public OrderEventPublisher(
    OrderEventRepository orderEventRepository,
    ObjectMapper objectMapper,
    EventStreamConfig.OrderEventStream source
  ) {
    this.orderEventRepository = orderEventRepository;
    this.objectMapper = objectMapper;
    this.source = source;
  }

  @Transactional
  public void placeOrder(OrderPlaced order) throws JsonProcessingException {
    orderEventRepository.save(OrderEvent
      .builder()
      .content(objectMapper.writeValueAsString(order))
      .eventType(order.type())
      .build());
  }

  @Scheduled(fixedRate = 2000)
  @Transactional
  public void publishExternally() {
    orderEventRepository
      .findAllBySentIsFalseOrderByEventTimestamp()
      .forEach(this::sentOrderEvent);
  }

  private void sentOrderEvent(OrderEvent event) {
    boolean success = source.orderChannel().send(MessageBuilder
      .withPayload(event.getContent())
      .setHeader("type", event.getEventType())
      .build(), 3000);
    if (success) event.sent();
  }
}
