package com.pitchrent.pitchesrental.dto.pitches;

import com.pitchrent.pitchesrental.dto.AddressDTO;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketballPitchDTO extends PitchDTO{
    private Integer numberOfBaskets;
    private Boolean fullSize;

    public BasketballPitchDTO(String uuid, String name, AddressDTO address, Long price, Integer minPeople, Integer maxPeople, PitchType pitchType, Boolean lights, Boolean rented, Boolean active, Long version, Integer numberOfBaskets, Boolean fullSize) {
        super(uuid, name, address, price, minPeople, maxPeople, pitchType, lights, rented, active, version);
        this.numberOfBaskets = numberOfBaskets;
        this.fullSize = fullSize;
    }
}
