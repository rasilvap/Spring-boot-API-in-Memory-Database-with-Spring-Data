package com.hackerrank.github.services;

import com.hackerrank.github.dto.EventDTO;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.utlis.DateUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final ActorService actorService;
    private final RepoService repoService;
    private final EventRepository eventRepository;

    public EventServiceImpl(ActorService actorService, EventRepository eventRepository, RepoService repoService){
        this.actorService = actorService;
        this.eventRepository = eventRepository;
        this.repoService = repoService;
    }

    @Override
    public ResponseEntity<Object> createEvent(EventDTO eventDTO) {
        if (eventRepository.findOne(eventDTO.getId()) != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Actor actor = actorService.createActor(eventDTO.getActor());
        Repo repo = repoService.createRepo(eventDTO.getRepo());
        Event event = new Event(eventDTO.getId(), eventDTO.getType(), actor, repo, DateUtils.createdAt(eventDTO));
        eventRepository.save(event);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<EventDTO>> findAllEvents() {
        List<Event> eventList = new ArrayList<>();
        eventRepository.findAll(new Sort(Sort.Direction.ASC, "id")).forEach(e -> eventList.add(e));
        return eventList.isEmpty() ?
                ResponseEntity.ok(new ArrayList<>()) :
                ResponseEntity.ok(eventList.stream()
                        .map(event -> EventDTO._toConvertFromEventEntity(event))
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Object> deleteAllEvents() {
        eventRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventDTO>> findAllEventsByActorIdOrderAscById(Long id) {
        List<Event> eventList = new ArrayList<>();
        eventRepository.findAllByActorIdOrderByIdAsc(id).forEach(e -> eventList.add(e));
        return eventList.isEmpty() ?
                ResponseEntity.ok(new ArrayList<>()) :
                ResponseEntity.ok(
                        eventList.stream()
                                .map(event -> EventDTO._toConvertFromEventEntity(event))
                                .collect(Collectors.toList()));

    }
}
