package com.pitchrent.pitchesrental.entities.pitches;

import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("BasketballPitch")
public class BasketballPitch extends Pitch {
    @PositiveOrZero
    private Integer numberOfBaskets;
    private Boolean fullSize;

    public BasketballPitch(String uuid, String name, Address address, Long price, Integer minPeople, Integer maxPeople, PitchType pitchType, Boolean lights, Boolean rented, Boolean active, Integer numberOfBaskets, Boolean fullSize) {
        super(uuid, name, address, price, minPeople, maxPeople, pitchType, lights, rented, active);
        this.numberOfBaskets = numberOfBaskets;
        this.fullSize = fullSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasketballPitch that = (BasketballPitch) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
