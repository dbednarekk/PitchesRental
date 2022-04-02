package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.entities.rentals.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Long> {

    Rental findRentalByUuid(String uuid);

    @Query("select rent from Rental rent where rent.pitch.uuid = ?1")
    List<Rental> getAllRentalsForPitch(String PitchUUID);

    @Query("select rent from Rental rent where rent.customer.account.login = ?1")
    List<Rental> getAllRentalsForCustomer(String login);
}
