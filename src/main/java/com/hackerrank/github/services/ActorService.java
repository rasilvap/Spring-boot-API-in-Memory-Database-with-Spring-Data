package com.hackerrank.github.services;

import com.hackerrank.github.dto.ActorDTO;
import com.hackerrank.github.model.Actor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ActorService {
     Actor createActor(ActorDTO actorDTO);
     ResponseEntity<ActorDTO> update(ActorDTO actorDTO);
     ResponseEntity<List<ActorDTO>> getActors();
     ResponseEntity<List<ActorDTO>> getActorsStreak();
}
