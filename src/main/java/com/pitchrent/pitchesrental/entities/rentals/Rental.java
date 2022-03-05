package com.pitchrent.pitchesrental.entities.rentals;

import com.pitchrent.pitchesrental.entities.users.Customer;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToOne
    private Customer customer;
    private Boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rental rental = (Rental) o;
        return id != null && Objects.equals(id, rental.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
