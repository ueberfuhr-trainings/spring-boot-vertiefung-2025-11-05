package de.schulung.spring.customers.domain.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerDeletedEvent(
  UUID uuid,
  LocalDateTime timestamp
) {

  public CustomerDeletedEvent(UUID uuid) {
    this(uuid, LocalDateTime.now());
  }

}
