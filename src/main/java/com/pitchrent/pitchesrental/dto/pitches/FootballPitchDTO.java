package com.pitchrent.pitchesrental.dto.pitches;

import com.pitchrent.pitchesrental.dto.AddressDTO;
import com.pitchrent.pitchesrental.entities.enums.GroundType;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballPitchDTO extends PitchDTO{
    private GroundType groundType;
    private Boolean goalNets;

    public FootballPitchDTO(String uuid, String name, AddressDTO address, Long price, Integer minPeople, Integer maxPeople, PitchType pitchType, Boolean lights, Boolean rented, Boolean active, Long version, GroundType groundType, Boolean goalNets) {
        super(uuid, name, address, price, minPeople, maxPeople, pitchType, lights, rented, active, version);
        this.groundType = groundType;
        this.goalNets = goalNets;
    }
}
