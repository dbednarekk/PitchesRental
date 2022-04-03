package com.pitchrent.pitchesrental.entities;

import com.pitchrent.pitchesrental.validators.AddressRegex;
import com.pitchrent.pitchesrental.validators.PostCode;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @AddressRegex
    private String street;
    @AddressRegex
    private String city;
    @AddressRegex
    private String country;
    @PostCode
    private String postCode;
    @Version
    @PositiveOrZero
    private Long version;

    public Address(String street, String city, String country, String postCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
