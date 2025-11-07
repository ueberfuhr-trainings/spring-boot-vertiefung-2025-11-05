package de.schulung.spring.customers.persistence.database;

import de.schulung.spring.customers.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
  componentModel = "spring",
  uses = {
    AddressEntityMapper.class,
  }
)
public interface CustomerEntityMapper {

  CustomerEntity map(Customer source);

  Customer map(CustomerEntity source);

  void copy(CustomerEntity source, @MappingTarget Customer target);

}
