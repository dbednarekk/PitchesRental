package com.pitchrent.pitchesrental.dto.pitches;

import com.pitchrent.pitchesrental.dto.AddressDTO;
import com.pitchrent.pitchesrental.entities.enums.PitchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBasketballPitchDTO {
    private String name;
    private AddressDTO address;
    private Long price;
    private Integer minPeople;
    private Integer maxPeople;
    private PitchType pitchType;
    private Boolean lights;
    private Boolean rented;
    private Boolean active;
    private Integer numberOfBaskets;
    private Boolean fullSize;
}
