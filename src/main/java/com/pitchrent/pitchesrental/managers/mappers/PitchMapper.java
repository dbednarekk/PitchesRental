package com.pitchrent.pitchesrental.managers.mappers;

import com.pitchrent.pitchesrental.dto.AddressDTO;
import com.pitchrent.pitchesrental.dto.pitches.*;
import com.pitchrent.pitchesrental.entities.Address;
import com.pitchrent.pitchesrental.entities.pitches.BasketballPitch;
import com.pitchrent.pitchesrental.entities.pitches.FootballPitch;
import com.pitchrent.pitchesrental.entities.pitches.Pitch;

import java.util.UUID;

public class PitchMapper {
    public PitchMapper() {
    }
    public static PitchDTO toPitchDTO( Pitch p){
        AddressDTO address = new AddressDTO(p.getAddress().getStreet(),p.getAddress().getCity(),p.getAddress().getCountry(),p.getAddress().getPostCode());
        return new PitchDTO(p.getUuid(),p.getName(),address, p.getPrice(),p.getMinPeople(),p.getMaxPeople()
               ,p.getPitchType(),p.getLights(),p.getRented(),p.getActive(),p.getVersion());
    }
    public static FootballPitchDTO toFootballPitchDTO(FootballPitch p){
        AddressDTO address = new AddressDTO(p.getAddress().getStreet(),p.getAddress().getCity(),p.getAddress().getCountry(),p.getAddress().getPostCode());
         return new FootballPitchDTO(p.getUuid(),p.getName(),address,p.getPrice()
                 ,p.getMinPeople(),p.getMaxPeople(),p.getPitchType(),p.getLights(),p.getRented(),p.getActive(),p.getVersion(),p.getGroundType(),p.getGoalNets());
    }
    public static BasketballPitchDTO toBasketballPitchDTO(BasketballPitch p){
        AddressDTO address = new AddressDTO(p.getAddress().getStreet(),p.getAddress().getCity(),p.getAddress().getCountry(),p.getAddress().getPostCode());
        return new BasketballPitchDTO(p.getUuid(),p.getName(),address,p.getPrice(),
                p.getMinPeople(),p.getMaxPeople(),p.getPitchType(),p.getLights(),p.getRented(),p.getActive(),p.getVersion(),p.getNumberOfBaskets(),p.getFullSize());
    }
    public static FootballPitch toFootballPitch(CreateFootballPitchDTO dto){
        Address address = new Address(dto.getAddress().getStreet(),dto.getAddress().getCity(),dto.getAddress().getCountry(),dto.getAddress().getPostCode());
        return new FootballPitch( UUID.randomUUID().toString(),dto.getName(),address,
                dto.getPrice(),dto.getMinPeople(),dto.getMaxPeople(),dto.getPitchType(),dto.getLights(),dto.getRented(),dto.getActive(),dto.getGroundType(),dto.getGoalNets());
    }
    public static BasketballPitch toBasketballPitch(CreateBasketballPitchDTO dto){
        Address address = new Address(dto.getAddress().getStreet(),dto.getAddress().getCity(),dto.getAddress().getCountry(),dto.getAddress().getPostCode());
        return new BasketballPitch( UUID.randomUUID().toString(),dto.getName(),address,
                dto.getPrice(),dto.getMinPeople(),dto.getMaxPeople(),dto.getPitchType(),dto.getLights(),dto.getRented(),dto.getActive(),dto.getNumberOfBaskets(),dto.getFullSize());
    }
}
