package com.pitchrent.pitchesrental.entities.rentals;

import com.pitchrent.pitchesrental.entities.pitches.FootballPitch;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("FootballPitchRental")
public class FootballPitchRental extends Rental {
    @OneToOne
    @JoinColumn(updatable = false, nullable = false, name = "football_pitch_id")
    private FootballPitch footballPitch;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FootballPitchRental that = (FootballPitchRental) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
