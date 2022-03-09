package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.entities.users.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

}
