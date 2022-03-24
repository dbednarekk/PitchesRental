package com.pitchrent.pitchesrental.entities.pitches;

import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.enums.GroundType;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("FootballPitch")
public class FootballPitch extends Pitch {
    private GroundType groundType;
    private Boolean goalNets;


    public FootballPitch(String uuid, String name, Address address, Long price, Integer minPeople, Integer maxPeople, PitchType pitchType, Boolean lights, Boolean rented, Boolean active, GroundType groundType, Boolean goalNets) {
        super(uuid, name, address, price, minPeople, maxPeople, pitchType, lights, rented, active);
        this.groundType = groundType;
        this.goalNets = goalNets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FootballPitch that = (FootballPitch) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
