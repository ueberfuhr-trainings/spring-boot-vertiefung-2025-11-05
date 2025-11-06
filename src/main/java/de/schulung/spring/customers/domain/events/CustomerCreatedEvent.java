package de.schulung.spring.customers.domain.events;

import de.schulung.spring.customers.domain.Customer;

import java.time.LocalDateTime;

public record CustomerCreatedEvent(
  Customer customer,
  LocalDateTime timestamp
) {

  public CustomerCreatedEvent(Customer customer) {
    this(customer, LocalDateTime.now());
  }

}
