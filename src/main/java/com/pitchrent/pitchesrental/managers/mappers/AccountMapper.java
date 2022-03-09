package com.pitchrent.pitchesrental.managers.mappers;

import com.pitchrent.pitchesrental.dto.AddressDTO;
import com.pitchrent.pitchesrental.dto.CustomerDTO;
import com.pitchrent.pitchesrental.dto.CustomerRegisterDTO;
import com.pitchrent.pitchesrental.entities.users.Customer;

public  class AccountMapper {
    private AccountMapper(){};

    public static CustomerDTO toCustomerDTO(Customer customer){
        AddressDTO addressDTO = new AddressDTO(customer.getAddress().getStreet(),customer.getAddress().getCity(),customer.getAddress().getCountry(),customer.getAddress().getPostCode());
        return new CustomerDTO(customer.getId(), customer.getLogin(), customer.getEmail(),customer.getFirstName(),customer.getLastName(),addressDTO);
    }
}
