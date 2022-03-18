package com.pitchrent.pitchesrental.managers.mappers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.pitchrent.pitchesrental.dto.*;
import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.users.AccessLevel;
import com.pitchrent.pitchesrental.entities.users.Account;
import com.pitchrent.pitchesrental.entities.users.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public  class AccountMapper {

    private AccountMapper(){};


    public static Customer toCustomerWithPassword( CustomerRegisterDTO dto){
        Address address = new Address(dto.getAddress().getStreet(),dto.getAddress().getCity(),dto.getAddress().getCountry(),dto.getAddress().getPostCode());
        return new Customer(dto.getFirstName(), dto.getLastName(),address);
    }
    public static Account toAccountWithPassword( CustomerRegisterDTO dto,String passwordhash){
        return new Account(dto.getLogin(), passwordhash,dto.getEmail(),true);
    }
    public static Account toAccountWithPassword(AccountRegisterDTO dto, String passwordhash){
        return new Account(dto.getLogin(), passwordhash,dto.getEmail(),true);
    }
    public static AccountDTO toAccountDTO(Account acc){
        return new AccountDTO(acc.getId(), acc.getLogin(), acc.getEmail(), acc.getActive(),acc.getAccessLevel().getAccessLevelType(),acc.getVersion());
    }
    public static CustomerDTO toCustomerDTO(Account acc, Customer cust){
        AddressDTO addressDTO = new AddressDTO(cust.getAddress().getStreet(),cust.getAddress().getCity(),cust.getAddress().getCountry(),cust.getAddress().getPostCode());
        return new CustomerDTO(acc.getId(), acc.getLogin(), acc.getEmail(), acc.getActive(), cust.getFirstName(), cust.getLastName(),addressDTO,acc.getVersion());
    }

}

