package com.pitchrent.pitchesrental.entities.rentals;

import com.pitchrent.pitchesrental.entities.users.Customer;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
   // @DateTimeFormat(iso = ISO.DATE)
    private LocalDateTime startDate;
   // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime endDate;
    @OneToOne
    @JoinColumn(updatable = false, nullable = false, name = "customer_id")
    private Customer customer;
    private Boolean active;
    @Version
    private Long version;

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
