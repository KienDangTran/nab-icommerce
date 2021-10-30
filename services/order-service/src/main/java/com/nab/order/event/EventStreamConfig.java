package com.nab.order.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@EnableBinding({EventStreamConfig.OrderEventStream.class})
public class EventStreamConfig {
  interface OrderEventStream {
    @Output("order-channel") MessageChannel orderChannel();
  }
}
