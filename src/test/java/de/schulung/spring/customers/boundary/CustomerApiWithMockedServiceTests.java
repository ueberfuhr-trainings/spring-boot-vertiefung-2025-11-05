package de.schulung.spring.customers.boundary;

import de.schulung.spring.customers.domain.CustomersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@BoundaryTest
public class CustomerApiWithMockedServiceTests {

  @Autowired
  MockMvc mvc;
  @Autowired
  CustomersService customersServiceMock;

  @Test
  void shouldReturnNotFoundWhenCustomerNotExists() throws Exception {

    var uuid = UUID.randomUUID();
    when(customersServiceMock.findById(uuid))
      .thenReturn(Optional.empty());

    mvc
      .perform(
        get("/customers/{uuid}", uuid)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNotFound());

  }

  @Test
  void shouldNotCreateCustomerOnValidationError() throws Exception {
    mvc.perform(
        post("/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
             {
                "birthdate": "2005-05-12",
               "state": "active"
            }
            """)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isBadRequest());

    verify(customersServiceMock, never()).create(any());

  }


}
