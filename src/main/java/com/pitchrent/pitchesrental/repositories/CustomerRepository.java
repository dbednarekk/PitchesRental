package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
