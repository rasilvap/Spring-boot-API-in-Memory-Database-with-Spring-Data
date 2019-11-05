package com.events.business.controller;

import com.events.business.dto.ActorDTO;
import com.events.business.dto.EventDTO;
import com.events.business.services.ActorService;
import com.events.business.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ActorEventsRestController {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private EventService eventService;
    @Autowired
    private ActorService actorService;

    @Autowired
    public ActorEventsRestController(EventService eventService, ActorService actorService) {
        this.eventService = eventService;
        this.actorService = actorService;
    }

    public ActorEventsRestController() {
    }

    @PostMapping(value = "/events")
    public ResponseEntity addEvent(@RequestBody EventDTO body) {
        return eventService.createEvent(body);
    }

    @GetMapping(value = "/events", produces = "application/json")
    public ResponseEntity<List<EventDTO>> findAllEvents() {
        return eventService.findAllEvents();
    }

    @DeleteMapping(value = "/erase")
    public ResponseEntity deleteEvents() {
        eventService.deleteAllEvents();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/events/actors/{actorID}", produces = "application/json")
    public ResponseEntity<List<EventDTO>> getAllEventsByActorId(@PathVariable Long actorID) {
        return eventService.findAllEventsByActorIdOrderAscById(actorID);
    }

    @PutMapping(value = "/actors", produces = "application/json")
    public ResponseEntity<ActorDTO> updateAvatarActorUrl(@RequestBody ActorDTO actorDTO) {
        return actorService.update(actorDTO);
    }

    @GetMapping(value = "/actors", produces = "application/json")
    public ResponseEntity<List<ActorDTO>> getActors() {
        return actorService.getActors();
    }

    @GetMapping(value = "/actors/streak", produces = "application/json")
    public ResponseEntity<List<ActorDTO>> getActorsStreak() {
        return actorService.getActorsStreak();
    }
}
