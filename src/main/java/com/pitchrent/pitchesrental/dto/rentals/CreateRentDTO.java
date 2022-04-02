package com.pitchrent.pitchesrental.dto.rentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String AccountLogin;
    private String PitchUUID;
}
