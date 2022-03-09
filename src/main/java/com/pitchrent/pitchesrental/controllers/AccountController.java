package com.pitchrent.pitchesrental.controllers;

import com.pitchrent.pitchesrental.dto.CustomerDTO;
import com.pitchrent.pitchesrental.dto.CustomerRegisterDTO;
import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.users.Customer;
import com.pitchrent.pitchesrental.managers.AccountManager;
import com.pitchrent.pitchesrental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/Account")
public class AccountController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired AccountManager accountManager;
    @PostMapping(path = "/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer( @RequestBody CustomerRegisterDTO customer){
        Customer res = new Customer();
        res.setLogin(customer.getLogin());
        res.setPassword(customer.getPassword());
        res.setEmail(customer.getEmail());
        res.setFirstName(customer.getFirstName());
        res.setLastName(customer.getLastName());
        res.setActive(true);
        Address adres = new Address();
        adres.setStreet(customer.getAddress().getStreet());
        adres.setCity(customer.getAddress().getCity());
        adres.setCountry(customer.getAddress().getCountry());
        adres.setPostCode(customer.getAddress().getPostCode());
        res.setAddress(adres);
        //TODO REMOVE THIS

        customerRepository.save(res);
    }
    @GetMapping(path = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers(){
        return accountManager.getAllCustomers();
    }
}
