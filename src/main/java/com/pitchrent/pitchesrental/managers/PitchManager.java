package com.pitchrent.pitchesrental.managers;

import com.pitchrent.pitchesrental.dto.AccountDTO;
import com.pitchrent.pitchesrental.dto.pitches.*;
import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.pitches.BasketballPitch;
import com.pitchrent.pitchesrental.entities.pitches.FootballPitch;
import com.pitchrent.pitchesrental.entities.pitches.Pitch;
import com.pitchrent.pitchesrental.entities.users.Account;
import com.pitchrent.pitchesrental.exceptions.AccountManagerException;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.exceptions.PitchManagerException;
import com.pitchrent.pitchesrental.managers.mappers.AccountMapper;
import com.pitchrent.pitchesrental.managers.mappers.PitchMapper;
import com.pitchrent.pitchesrental.repositories.BasketballPitchRepository;
import com.pitchrent.pitchesrental.repositories.FootballPitchRepository;
import com.pitchrent.pitchesrental.repositories.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

import static com.pitchrent.pitchesrental.common.I18n.*;

@ManagedBean
public class PitchManager {

    @Autowired
    private PitchRepository pitchRepository;
    @Autowired
    private FootballPitchRepository footballPitchRepository;
    @Autowired
    private BasketballPitchRepository basketballPitchRepository;


    public List<PitchDTO> getAll(){
        List<PitchDTO> res = new ArrayList<>();
        for (Pitch p : pitchRepository.findAll()
        ) {
            res.add(PitchMapper.toPitchDTO(p));
        }
        return res;
    }

    public List<FootballPitchDTO> getAllFootballPitches(){
        List<FootballPitchDTO> res = new ArrayList<>();
        for (FootballPitch p : footballPitchRepository.findAll()
        ) {
            res.add(PitchMapper.toFootballPitchDTO(p));
        }
        return res;
    }
    public List<BasketballPitchDTO> getAllBasketballPitches(){
        List<BasketballPitchDTO> res = new ArrayList<>();
        for (BasketballPitch p : basketballPitchRepository.findAll()
        ) {
            res.add(PitchMapper.toBasketballPitchDTO(p));
        }
        return res;
    }

    public void createFootballPitch(CreateFootballPitchDTO dto) throws PitchManagerException {
        for(Pitch p : pitchRepository.findAll()){
            if(p.getName().equals(dto.getName())) throw new PitchManagerException(NAME_TAKEN);
        }
        FootballPitch pitch = PitchMapper.toFootballPitch(dto);
        pitchRepository.save(pitch);
    }

    public void createBasketballPitch(CreateBasketballPitchDTO dto) throws PitchManagerException {
        for(Pitch p : pitchRepository.findAll()){
            if(p.getName().equals(dto.getName())) throw new PitchManagerException(NAME_TAKEN);
        }
        BasketballPitch pitch = PitchMapper.toBasketballPitch(dto);
        pitchRepository.save(pitch);
    }
    public PitchDTO getPitchByUUID( String uuid) throws PitchManagerException {
        try {
            return PitchMapper.toPitchDTO(pitchRepository.findPitchByUuid(uuid));
        }catch (NullPointerException e){
            throw new PitchManagerException(PITCH_NOT_FOUND);
        }
    }
    public FootballPitchDTO getFootballPitchByUUID( String uuid) throws PitchManagerException {
        try {
            return PitchMapper.toFootballPitchDTO(footballPitchRepository.findFootballPitchByUuid(uuid));
        }catch (NullPointerException e){
            throw new PitchManagerException(PITCH_NOT_FOUND);
        }
    }
    public BasketballPitchDTO getBasketballPitchByUUID( String uuid) throws PitchManagerException {
        try {
            return PitchMapper.toBasketballPitchDTO(basketballPitchRepository.findBasketballPitchByUuid(uuid));
        }catch (NullPointerException e){
            throw new PitchManagerException(PITCH_NOT_FOUND);
        }
    }

    public void updateFootballPitch( FootballPitchDTO dto) throws BaseException {
        FootballPitch p = footballPitchRepository.findFootballPitchByUuid(dto.getUuid());
        if( p.getRented()) throw new PitchManagerException(PITCH_RESERVED);
        checkForOptimisticLock(p.getVersion(),dto.getVersion());
        Address address = new Address(dto.getAddress().getStreet(),dto.getAddress().getCity(),dto.getAddress().getCountry(),dto.getAddress().getPostCode());
        p.setAddress(address);
        p.setPrice(dto.getPrice());
        p.setMinPeople(dto.getMinPeople());
        p.setMaxPeople(dto.getMaxPeople());
        p.setPitchType(dto.getPitchType());
        p.setLights(dto.getLights());
        p.setGroundType(dto.getGroundType());
        p.setGoalNets(dto.getGoalNets());
        footballPitchRepository.save(p);
    }
    public void updateBasketballPitch( BasketballPitchDTO dto) throws BaseException {
        BasketballPitch p = basketballPitchRepository.findBasketballPitchByUuid(dto.getUuid());
        if( p.getRented()) throw new PitchManagerException(PITCH_RESERVED);
        checkForOptimisticLock(p.getVersion(),dto.getVersion());
        Address address = new Address(dto.getAddress().getStreet(),dto.getAddress().getCity(),dto.getAddress().getCountry(),dto.getAddress().getPostCode());
        p.setAddress(address);
        p.setPrice(dto.getPrice());
        p.setMinPeople(dto.getMinPeople());
        p.setMaxPeople(dto.getMaxPeople());
        p.setPitchType(dto.getPitchType());
        p.setLights(dto.getLights());
        p.setNumberOfBaskets(dto.getNumberOfBaskets());
        p.setFullSize(dto.getFullSize());
        basketballPitchRepository.save(p);
    }

    public void disablePitch( String uuid){
        Pitch p = pitchRepository.findPitchByUuid(uuid);
        //TODO change to check for optimistic lock exception
        p.setActive(!p.getActive());
        pitchRepository.save(p);
    }

    public void deleteFootballPitch(String uuid) throws PitchManagerException {
        FootballPitch p = footballPitchRepository.findFootballPitchByUuid(uuid);
        if( p.getRented()) throw new PitchManagerException(PITCH_RESERVED);
        //TODO check if rent is active or not - if not, you can remove pitch with all its reservation
        footballPitchRepository.delete(p);
    }
    public void deleteBasketballPitch(String uuid) throws PitchManagerException {
        BasketballPitch p = basketballPitchRepository.findBasketballPitchByUuid(uuid);
        if( p.getRented()) throw new PitchManagerException(PITCH_RESERVED);
        //TODO check if rent is active or not - if not, you can remove pitch with all its reservation
        basketballPitchRepository.delete(p);
    }
    public static void checkForOptimisticLock(Long v1, Long v2) throws BaseException {
        if(!v1.equals(v2)) throw new AccountManagerException(OPTIMISTIC_LOCK_EXCEPTION);
    }
}
