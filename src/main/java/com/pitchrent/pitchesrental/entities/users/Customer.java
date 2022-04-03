package com.pitchrent.pitchesrental.entities.users;

import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.validators.CustomerName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DiscriminatorValue("Customer")
public class Customer extends AccessLevel{
    @CustomerName
    private String firstName;
    @CustomerName
    private String lastName;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(updatable = false, nullable = false, name = "address_id")
    private Address address;


    public Customer(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    @Override
    public String getAccessLevelType() {
        return "Customer";
    }
}
