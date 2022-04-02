package com.pitchrent.pitchesrental.dto.rentals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pitchrent.pitchesrental.common.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentDTO implements SignableEntity {
    private String uuid;
    private LocalDateTime start_date_rental;
    private LocalDateTime end_date_rental;
    private Long version;


    @JsonIgnore
    @Override
    public String getSignablePayload() { return uuid + "." + version;
    }
}
