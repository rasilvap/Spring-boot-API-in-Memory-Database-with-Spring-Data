package com.hackerrank.github.services;

import com.hackerrank.github.dto.ActorDTO;
import com.hackerrank.github.dto.ActorOrderedDTO;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ActorServiceImp implements ActorService {

    private final ActorRepository actorRepository;
    private final EventRepository eventRepository;

    public ActorServiceImp(ActorRepository actorRepository, EventRepository eventRepository) {
        this.actorRepository = actorRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Actor createActor(ActorDTO actorDTO) {
        Actor actor = new Actor();
        BeanUtils.copyProperties(actorDTO, actor);
        return actorRepository.save(actor);
    }

    @Override
    public ResponseEntity<ActorDTO> update(ActorDTO actorDTO) {
        Actor actor = actorRepository.findOne(actorDTO.getId());
        if (Objects.isNull(actor)) {
            return ResponseEntity.notFound().build();
        }
        if (!actorDTO.getLogin().equals(actor.getLogin())) {
            return ResponseEntity.badRequest().build();
        }
        actor.setAvatar(actorDTO.getAvatar());
        Actor actorUpdated = actorRepository.save(actor);
        return ResponseEntity.ok(ActorDTO._toConvertFromActorEntity(actorUpdated));
    }

    @Override
    public ResponseEntity<List<ActorDTO>> getActors() {
        List<Event> events = eventRepository.findAll();
        List<Actor> actors = actorRepository.findAll();
        List<ActorOrderedDTO> actorOrderedDTO = new ArrayList<>();
        for (Actor actor : actors) {
            List<Event> collect = events.stream()
                    .filter(event -> event.getActor().equals(actor))
                    .sorted(Comparator.comparing(Event::getCreatedAt).reversed())
                    .collect(Collectors.toList());
            if (!collect.isEmpty()) {
                actorOrderedDTO.add(new ActorOrderedDTO(actor, collect.size(), collect.get(0).getCreatedAt()));
            }
        }
        List<ActorDTO> actorList = getCollectionWithCriteria(actorOrderedDTO);
        return ResponseEntity.ok(actorList);
    }

    @Override
    public ResponseEntity<List<ActorDTO>> getActorsStreak() {
        List<Event> events = eventRepository.findAll();
        List<Actor> actors = actorRepository.findAll();
        List<ActorOrderedDTO> actorPairStreaks = new ArrayList<>();
        for (Actor actor : actors) {
            List<Event> collect = events.stream()
                    .filter(event -> event.getActor().equals(actor) && event.getType().equals("PushEvent"))
                    .sorted(Comparator.comparing(Event::getCreatedAt).reversed())
                    .collect(Collectors.toList());

            if (!collect.isEmpty()) {
                if (collect.size() == 1) {
                    actorPairStreaks.add(new ActorOrderedDTO(actor, 0, collect.get(0).getCreatedAt()));
                } else {
                    Integer mayorStreak = getStreak(collect);
                    actorPairStreaks.add(new ActorOrderedDTO(actor, mayorStreak, collect.get(0).getCreatedAt()));
                }
            }
        }
        List<ActorDTO> actorList = getCollectionWithCriteria(actorPairStreaks);
        return ResponseEntity.ok(actorList);
    }

    private List<ActorDTO> getCollectionWithCriteria(List<ActorOrderedDTO> actorOrderedDTOS) {
        return actorOrderedDTOS.stream()
                .sorted(Comparator.comparing(o -> o.getActor().getLogin()))
                .sorted(Comparator.comparing(ActorOrderedDTO::getLast).reversed())
                .sorted(Comparator.comparing(ActorOrderedDTO::getcEvents).reversed())
                .map(ActorOrderedDTO::getActor)
                .map(ActorDTO::_toConvertFromActorEntity)
                .collect(Collectors.toList());
    }

    private int getStreak(List<Event> collect) {
        int mayorStreak = 0;
        int streak = 0;
        for (int i = collect.size() - 1; i > 0; i--) {
            LocalDateTime currentDate = collect.get(i).getCreatedAt().toLocalDateTime();
            LocalDateTime nextDate = collect.get(i - 1).getCreatedAt().toLocalDateTime();
            LocalDateTime currentDateEndOfDay = currentDate.with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay());

            long hours = ChronoUnit.HOURS.between(currentDate, nextDate);
            long hoursFinalDay = ChronoUnit.HOURS.between(currentDate, currentDateEndOfDay);
            long days = ChronoUnit.DAYS.between(currentDate, nextDate);

            if (currentDate.getDayOfMonth() == nextDate.getDayOfMonth() || days > 1) {
                streak = 0;
            } else if (hours - hoursFinalDay <= 24) {
                streak++;
                if (streak > mayorStreak)
                    mayorStreak = streak;
            }
        }
        return mayorStreak;
    }
}
