package com.pitchrent.pitchesrental.entities.rentals;

import com.pitchrent.pitchesrental.entities.pitches.Pitch;
import com.pitchrent.pitchesrental.entities.users.Customer;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
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
    @NotNull
    @Column(nullable = false,updatable = false)
    private String uuid;
   // @DateTimeFormat(iso = ISO.DATE)
    private LocalDateTime startDate;
   // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime endDate;
    @OneToOne
    @JoinColumn(updatable = false, nullable = false, name = "customer_id")
    private Customer customer;
    private Boolean active;
    @OneToOne
    @JoinColumn(updatable = false, nullable = false, name = "pitch_id")
    private Pitch pitch;
    @Version
    private Long version;


    public Rental(String uuid, LocalDateTime startDate, LocalDateTime endDate, Customer customer, Boolean active, Pitch pitch) {
        this.uuid = uuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.active = active;
        this.pitch = pitch;
    }

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
