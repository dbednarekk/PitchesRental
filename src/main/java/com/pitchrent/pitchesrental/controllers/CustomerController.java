package com.pitchrent.pitchesrental.controllers;

import com.pitchrent.pitchesrental.dto.CustomerDTO;
import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.Customer;
import com.pitchrent.pitchesrental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer( @RequestBody CustomerDTO customer){
        Customer res = new Customer();
        res.setLogin(customer.getLogin());
        res.setPassword(customer.getPassword());
        res.setEmail(customer.getEmail());
        res.setFirstName(customer.getFirstName());
        res.setLastName(customer.getLastName());
        Address adres = new Address();
        adres.setStreet(customer.getAddress().getStreet());
        adres.setCity(customer.getAddress().getCity());
        adres.setCountry(customer.getAddress().getCountry());
        adres.setPostCode(customer.getAddress().getPostCode());
        res.setAddress(adres);
        //TODO REMOVE THIS

        customerRepository.save(res);
    }
}
