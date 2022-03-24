package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.entities.pitches.BasketballPitch;
import org.springframework.data.repository.CrudRepository;

public interface BasketballPitchRepository extends CrudRepository<BasketballPitch,Long> {
    BasketballPitch findBasketballPitchByUuid(String uuid);
}
