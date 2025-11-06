package de.schulung.spring.customers.domain;

import de.schulung.spring.customers.domain.events.CustomerCreatedEvent;
import de.schulung.spring.customers.domain.events.CustomerDeletedEvent;
import de.schulung.spring.customers.domain.events.CustomerUpdatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DomainTest
class CustomerServiceEventsTests {

  @Autowired
  ApplicationEvents applicationEvents;
  @Autowired
  CustomersService customersService;

  @Test
  void shouldPublishEventOnCustomerCreated() {

    var customer = Customer
      .builder()
      .name("John Doe")
      .birthdate(LocalDate.of(1980, Month.APRIL, 1))
      .build();
    customersService.create(customer);

    assertThat(applicationEvents.stream(CustomerCreatedEvent.class))
      .hasSize(1)
      .first()
      .extracting(CustomerCreatedEvent::customer)
      .isSameAs(customer);

  }

  @Test
  void shouldNotPublishEventOnInvalidCustomerCreated() {

    var customer = Customer
      .builder()
      .birthdate(LocalDate.of(1980, Month.APRIL, 1))
      .build();
    assertThatThrownBy(() -> customersService.create(customer))
      .isNotNull();
    assertThat(applicationEvents.stream(CustomerCreatedEvent.class))
      .isEmpty();

  }

  @Test
  void shouldPublishEventOnCustomerUpdated() {

    var customer = Customer
      .builder()
      .name("John Doe")
      .birthdate(LocalDate.of(1980, Month.APRIL, 1))
      .build();
    customersService.create(customer);
    customersService.update(customer);

    assertThat(applicationEvents.stream(CustomerUpdatedEvent.class))
      .hasSize(1)
      .first()
      .extracting(CustomerUpdatedEvent::customer)
      .isSameAs(customer);

  }

  @Test
  void shouldPublishEventOnCustomerDeleted() {

    var customer = Customer
      .builder()
      .name("John Doe")
      .birthdate(LocalDate.of(1980, Month.APRIL, 1))
      .build();
    customersService.create(customer);
    customersService.delete(customer.getUuid());

    assertThat(applicationEvents.stream(CustomerDeletedEvent.class))
      .hasSize(1)
      .first()
      .extracting(CustomerDeletedEvent::uuid)
      .isEqualTo(customer.getUuid());

  }

}
