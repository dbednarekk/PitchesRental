package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.entities.pitches.FootballPitch;
import org.springframework.data.repository.CrudRepository;

public interface FootballPitchRepository extends CrudRepository<FootballPitch,Long> {
    FootballPitch findFootballPitchByUuid(String uuid);
}
