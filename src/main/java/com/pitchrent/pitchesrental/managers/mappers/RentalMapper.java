package com.pitchrent.pitchesrental.managers.mappers;

import com.pitchrent.pitchesrental.dto.rentals.CreateRentDTO;
import com.pitchrent.pitchesrental.dto.rentals.RentalDTO;
import com.pitchrent.pitchesrental.entities.pitches.Pitch;
import com.pitchrent.pitchesrental.entities.rentals.Rental;
import com.pitchrent.pitchesrental.entities.users.Customer;

import java.util.UUID;

public class RentalMapper {

    public RentalMapper() {
    }

    public static RentalDTO toRentalDTO(Rental r){
        return new RentalDTO(r.getUuid(), r.getCustomer().getAccount().getLogin(), r.getPitch().getUuid(),
                r.getStartDate(), r.getEndDate(), r.getActive(), r.getVersion());
    }

    public static Rental toRental(CreateRentDTO dto, Customer customer, Pitch pitch){
        return new Rental(UUID.randomUUID().toString(), dto.getStartDate(), dto.getEndDate(),
                customer, true, pitch);
    }
}
