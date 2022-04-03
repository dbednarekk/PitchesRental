package com.pitchrent.pitchesrental.managers;

import com.pitchrent.pitchesrental.dto.rentals.CreateRentDTO;
import com.pitchrent.pitchesrental.dto.rentals.RentalDTO;
import com.pitchrent.pitchesrental.dto.rentals.UpdateRentDTO;
import com.pitchrent.pitchesrental.entities.pitches.Pitch;
import com.pitchrent.pitchesrental.entities.rentals.Rental;
import com.pitchrent.pitchesrental.entities.users.Customer;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.exceptions.RentalManagerException;
import com.pitchrent.pitchesrental.exceptions.RepositoryException;
import com.pitchrent.pitchesrental.managers.mappers.RentalMapper;
import com.pitchrent.pitchesrental.repositories.AccountRepository;
import com.pitchrent.pitchesrental.repositories.PitchRepository;
import com.pitchrent.pitchesrental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pitchrent.pitchesrental.common.I18n.*;
import static com.pitchrent.pitchesrental.common.Utils.checkForOptimisticLock;

@ManagedBean
public class RentalManager {


    private final RentalRepository rentalRepository;

    private final AccountRepository accountRepository;

    private final PitchRepository pitchRepository;

    @Autowired
    public RentalManager(RentalRepository rentalRepository, AccountRepository accountRepository, PitchRepository pitchRepository) {
        this.rentalRepository = rentalRepository;
        this.accountRepository = accountRepository;
        this.pitchRepository = pitchRepository;
    }


    public List<RentalDTO> getAllRentals() {
        List<RentalDTO> res = new ArrayList<>();
        for (Rental r : rentalRepository.findAll()
        ) {
            res.add(RentalMapper.toRentalDTO(r));
        }
        return res;
    }

    public RentalDTO getRentByUUID(String uuid) throws BaseException {
        try {
            return RentalMapper.toRentalDTO(rentalRepository.findRentalByUuid(uuid));
        }catch (NullPointerException e){
            throw new RepositoryException(RENTAL_NOT_FOUND);
        }
    }

    public void createRental(CreateRentDTO dto) {
        Customer customer = (Customer) accountRepository.findCustomerByLogin(dto.getAccountLogin())
                .getAccessLevel();
        Pitch pitch = pitchRepository.findPitchByUuid(dto.getPitchUUID());
        rentalRepository.save(RentalMapper.toRental(dto, customer, pitch));
        pitch.setRented(true);
        pitchRepository.save(pitch);
    }

    public void updateRent(UpdateRentDTO dto) throws BaseException {
        Rental rent = rentalRepository.findRentalByUuid(dto.getUuid());
        if (!rent.getActive()) throw new RentalManagerException(RENT_CANNOT_UPDATE);
        checkForOptimisticLock(dto.getVersion(), rent.getVersion());
        rent.setStartDate(dto.getStart_date_rental());
        rent.setEndDate(dto.getEnd_date_rental());
        rentalRepository.save(rent);
    }

    public void removeRental(String uuid) throws RentalManagerException {
        Rental rent = rentalRepository.findRentalByUuid(uuid);
        if (rent.getActive()) throw new RentalManagerException(RENT_CANNOT_REMOVE);
        rentalRepository.delete(rent);
    }

    public void endRental(String uuid, Long version) throws BaseException {
        Rental rent = rentalRepository.findRentalByUuid(uuid);
        if (LocalDateTime.now().isBefore(rent.getEndDate()))
            checkForOptimisticLock(rent.getVersion(), version);
        rent.setActive(false);
        rentalRepository.save(rent);
        Pitch p = pitchRepository.findPitchByUuid(rent.getPitch().getUuid());
        p.setRented(false);
        pitchRepository.save(p);

    }

    public List<RentalDTO> getRentsForPitch(String pitchUUID) {
        List<RentalDTO> res = new ArrayList<>();
        for (Rental r : rentalRepository.getAllRentalsForPitch(pitchUUID)
        ) {
            res.add(RentalMapper.toRentalDTO(r));
        }
        return res;
    }

    public List<RentalDTO> getRentsForCustomer(String login) {
        List<RentalDTO> res = new ArrayList<>();
        for (Rental r : rentalRepository.getAllRentalsForCustomer(login)
        ) {
            res.add(RentalMapper.toRentalDTO(r));
        }
        return res;
    }
}
