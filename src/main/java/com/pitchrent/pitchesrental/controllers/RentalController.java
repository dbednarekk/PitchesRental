package com.pitchrent.pitchesrental.controllers;

import com.pitchrent.pitchesrental.common.EtagIntegrityVerifier;
import com.pitchrent.pitchesrental.dto.rentals.CreateRentDTO;
import com.pitchrent.pitchesrental.dto.rentals.RentalDTO;
import com.pitchrent.pitchesrental.dto.rentals.UpdateRentDTO;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.exceptions.RentalManagerException;
import com.pitchrent.pitchesrental.exceptions.RepositoryException;
import com.pitchrent.pitchesrental.managers.RentalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/Rental")
public class RentalController {

    private final RentalManager rentalManager;

    @Autowired
    public RentalController(RentalManager rentalManager) {
        this.rentalManager = rentalManager;
    }


    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<RentalDTO> getAllRentals() {
        return rentalManager.getAllRentals();
    }

    @GetMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDTO getRentByUUID(@PathVariable String uuid) throws BaseException {
        return rentalManager.getRentByUUID(uuid);
    }

    @PostMapping(path = "/addRent")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRent(@RequestBody CreateRentDTO dto) {
        rentalManager.createRental(dto);
    }

    @PutMapping(path = "/updateRent")
    public ResponseEntity<String> updateRent(@RequestBody UpdateRentDTO dto, @RequestHeader("If-Match") String Etag) throws BaseException {
        if (!EtagIntegrityVerifier.verifyEntityIntegrity(dto, Etag)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Invalid Etag");
        }
        rentalManager.updateRent(dto);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/deleteRent/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRent(@PathVariable String uuid) throws RentalManagerException {
        rentalManager.removeRental(uuid);
    }

    @PatchMapping("/endRental/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void endRental(@PathVariable String uuid, @RequestBody Long version) throws BaseException {

        rentalManager.endRental(uuid, version);
    }

    @GetMapping("/rentsForPitch/{pitchUUID}")
    @ResponseStatus(HttpStatus.OK)
    public List<RentalDTO> getRentsForPitch(@PathVariable String pitchUUID) {
        return rentalManager.getRentsForPitch(pitchUUID);
    }


    @GetMapping("/rentsForCustomer/{login}")
    @ResponseStatus(HttpStatus.OK)
    public List<RentalDTO> getRentsForCustomer(@PathVariable String login) {
        return rentalManager.getRentsForCustomer(login);
    }


}
