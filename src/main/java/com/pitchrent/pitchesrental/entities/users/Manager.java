package com.pitchrent.pitchesrental.entities.users;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString

public class Manager extends Account{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Manager manager = (Manager) o;
        return getId() != null && Objects.equals(getId(), manager.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
