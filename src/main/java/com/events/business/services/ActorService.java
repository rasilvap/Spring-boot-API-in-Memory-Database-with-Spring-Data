package com.events.business.services;

import com.events.business.model.Actor;
import com.events.business.dto.ActorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActorService {
     Actor createActor(ActorDTO actorDTO);
     ResponseEntity<ActorDTO> update(ActorDTO actorDTO);
     ResponseEntity<List<ActorDTO>> getActors();
     ResponseEntity<List<ActorDTO>> getActorsStreak();
}
