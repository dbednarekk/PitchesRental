package com.pitchrent.pitchesrental.entities.rentals;

import com.pitchrent.pitchesrental.entities.pitches.BasketballPitch;
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
@DiscriminatorValue("BasketballPitchRental")
public class BasketballPitchRental extends Rental {
    @OneToOne
    @JoinColumn(updatable = false, nullable = false, name = "basketball_pitch_id")
    private BasketballPitch basketballPitch;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasketballPitchRental that = (BasketballPitchRental) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
