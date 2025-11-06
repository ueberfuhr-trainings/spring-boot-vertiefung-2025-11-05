package de.schulung.spring.customers.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DomainTest
class CustomerInitializationDisabledTests {

  @Autowired
  CustomersService customersService;

  @Test
  void shouldInitializeCustomer() {
    assertThat(this.customersService.count())
      .isEqualTo(0);
  }

}
