package de.schulung.spring.customers.domain.events;

import de.schulung.spring.customers.domain.Customer;

import java.time.LocalDateTime;

public record CustomerUpdatedEvent(
  Customer customer,
  LocalDateTime timestamp
) {

  public CustomerUpdatedEvent(Customer customer) {
    this(customer, LocalDateTime.now());
  }

}
