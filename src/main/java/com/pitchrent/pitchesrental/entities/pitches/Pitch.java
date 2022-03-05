package com.pitchrent.pitchesrental.entities.pitches;

import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pitch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    private Address address;
    private Long price;
    private Integer minPeople;
    private Integer maxPeople;
    private PitchType pitchType;
    private Boolean lights;
    private Boolean rented;
    private Boolean active;

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


