package com.pitchrent.pitchesrental.managers;

import com.pitchrent.pitchesrental.dto.CustomerDTO;
import com.pitchrent.pitchesrental.entities.users.Customer;
import com.pitchrent.pitchesrental.managers.mappers.AccountMapper;
import com.pitchrent.pitchesrental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class AccountManager {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> res = new ArrayList<>();
        for (Customer cust: customerRepository.findAll()
             ) {
            res.add(AccountMapper.toCustomerDTO(cust));
        }
        return res;
    }
}
