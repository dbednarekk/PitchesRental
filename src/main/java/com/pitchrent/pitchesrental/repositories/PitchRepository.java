package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.dto.pitches.PitchDTO;
import com.pitchrent.pitchesrental.entities.pitches.FootballPitch;
import com.pitchrent.pitchesrental.entities.pitches.Pitch;
import com.pitchrent.pitchesrental.entities.users.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PitchRepository extends CrudRepository<Pitch,Long> {
        Pitch findPitchByUuid(String uuid);
}
