package com.nab.order.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_event", schema = "PUBLIC")
public class OrderEvent {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @Builder.Default
  private Boolean sent = false;

  @Builder.Default
  private Instant eventTimestamp = Instant.now();

  private String eventType;

  public void sent() {
    sent = true;
  }
}
