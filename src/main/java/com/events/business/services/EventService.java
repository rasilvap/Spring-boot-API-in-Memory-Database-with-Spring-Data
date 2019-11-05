package com.events.business.services;

import com.events.business.dto.EventDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {
    ResponseEntity<Object> createEvent(EventDTO actorDTO);
    ResponseEntity<List<EventDTO>> findAllEvents();
    ResponseEntity<Object> deleteAllEvents();
    ResponseEntity<List<EventDTO>> findAllEventsByActorIdOrderAscById(Long id);
}
