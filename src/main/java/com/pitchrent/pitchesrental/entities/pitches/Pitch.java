package com.pitchrent.pitchesrental.entities.pitches;

import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "pitch", discriminatorType = DiscriminatorType.STRING)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class Pitch {
    @Id
    @SequenceGenerator(name = "PITCH_SEQ_GEN", sequenceName = "pitch_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PITCH_SEQ_GEN")
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Column(nullable = false,updatable = false)
    private String uuid;
    @Column(unique=true)
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(updatable = false, nullable = false, name = "address_id")
    private Address address;
    @PositiveOrZero
    private Long price;
    @PositiveOrZero
    private Integer minPeople;
    @Min(2)
    private Integer maxPeople;
    private PitchType pitchType;
    private Boolean lights;
    private Boolean rented;
    private Boolean active;
    @Version
    private Long version;

    public Pitch(String uuid, String name, Address address, Long price, Integer minPeople, Integer maxPeople, PitchType pitchType, Boolean lights, Boolean rented, Boolean active) {
        this.uuid = uuid;
        this.name = name;
        this.address = address;
        this.price = price;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.pitchType = pitchType;
        this.lights = lights;
        this.rented = rented;
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pitch pitch = (Pitch) o;
        return id != null && Objects.equals(id, pitch.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


