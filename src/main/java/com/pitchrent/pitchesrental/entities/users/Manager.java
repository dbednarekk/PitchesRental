package com.pitchrent.pitchesrental.entities.users;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("Manager")
public class Manager extends AccessLevel{

    @Override
    public String getAccessLevelType() {
        return "Manager";
    }
}
