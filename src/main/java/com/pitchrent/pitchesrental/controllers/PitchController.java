package com.pitchrent.pitchesrental.controllers;

import com.pitchrent.pitchesrental.common.EtagIntegrityVerifier;
import com.pitchrent.pitchesrental.dto.pitches.*;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.exceptions.ETagException;
import com.pitchrent.pitchesrental.managers.PitchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pitchrent.pitchesrental.common.I18n.ETAG_EXCEPTION;

@RestController
@RequestMapping(path = "/Pitch")
public class PitchController {
    private final PitchManager pitchManager;

    @Autowired
    public PitchController(PitchManager pitchManager) {
        this.pitchManager = pitchManager;
    }


    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PitchDTO> getAllPitches() {
        return pitchManager.getAll();
    }

    @GetMapping(path = "/footballPitches")
    @ResponseStatus(HttpStatus.OK)
    public List<FootballPitchDTO> getAllFootballPitches() {
        return pitchManager.getAllFootballPitches();
    }

    @GetMapping(path = "/basketballPitches")
    @ResponseStatus(HttpStatus.OK)
    public List<BasketballPitchDTO> getAllBasketballPitches() {
        return pitchManager.getAllBasketballPitches();
    }


    @GetMapping(path = "/getPitch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PitchDTO> getPitchByUUID(@PathVariable String uuid) throws BaseException {
        return ResponseEntity.ok().eTag(EtagIntegrityVerifier.calculateEntitySignature(pitchManager.getPitchByUUID(uuid)))
                .body(pitchManager.getPitchByUUID(uuid));
    }

    @GetMapping(path = "/getFootballPitch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FootballPitchDTO> getFootballPitchByUUID(@PathVariable String uuid) throws BaseException {
        return ResponseEntity.ok().eTag(EtagIntegrityVerifier.calculateEntitySignature(pitchManager.getFootballPitchByUUID(uuid)))
                .body(pitchManager.getFootballPitchByUUID(uuid));
    }

    @GetMapping(path = "/getBasketballPitch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BasketballPitchDTO> getBasketballPitchByUUID(@PathVariable String uuid) throws BaseException {
        return ResponseEntity.ok().eTag(EtagIntegrityVerifier.calculateEntitySignature(pitchManager.getBasketballPitchByUUID(uuid)))
                .body(pitchManager.getBasketballPitchByUUID(uuid));
    }


    @PostMapping(path = "/addFootballPitch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFootballPitch(@RequestBody CreateFootballPitchDTO dto) throws BaseException {
        pitchManager.createFootballPitch(dto);
    }

    @PostMapping(path = "/addBasketballPitch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBasketballPitch(@RequestBody CreateBasketballPitchDTO dto) throws BaseException {
        pitchManager.createBasketballPitch(dto);
    }

    @PutMapping(path = "/changeFootballPitch")
    @ResponseStatus(HttpStatus.OK)
    public void updateFootballPitch(@RequestBody FootballPitchDTO dto, @RequestHeader("If-Match") String Etag) throws BaseException {
        if (!EtagIntegrityVerifier.verifyEntityIntegrity(dto, Etag)) {
            throw new ETagException(ETAG_EXCEPTION);
        }
        pitchManager.updateFootballPitch(dto);
    }

    @PutMapping(path = "/changeBasketballPitch")
    @ResponseStatus(HttpStatus.OK)
    public void updateBasketballPitch(@RequestBody BasketballPitchDTO dto, @RequestHeader("If-Match") String Etag) throws BaseException {
        if (!EtagIntegrityVerifier.verifyEntityIntegrity(dto, Etag)) {
            throw new ETagException(ETAG_EXCEPTION);
        }
        pitchManager.updateBasketballPitch(dto);
    }

    @PatchMapping(path = "/disablePitch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void disablePitch(@PathVariable String uuid, @RequestBody Long version) throws BaseException {
        pitchManager.disablePitch(uuid, version);
    }

    @DeleteMapping(path = "/deleteFootballPitch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFootballPitch(@PathVariable String uuid) throws BaseException {
        pitchManager.deleteFootballPitch(uuid);
    }

    @DeleteMapping(path = "/deleteBasketballPitch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBasketballPitch(@PathVariable String uuid) throws BaseException {
        pitchManager.deleteBasketballPitch(uuid);
    }
}
