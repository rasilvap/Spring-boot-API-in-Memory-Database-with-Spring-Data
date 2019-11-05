package com.events.business.dto;

import com.events.business.model.Event;
import com.events.business.utlis.DateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventDTO {

    private Long id;
    private String type;
    private ActorDTO actor;
    private RepoDTO repo;
    @JsonProperty(value = "created_at")
    private String createdAt;

    public EventDTO(){}

    public EventDTO(Long id, String type, ActorDTO actor, RepoDTO repo, String createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

    public static EventDTO _toConvertFromEventEntity(Event event) {
        return new EventDTO(event.getId(), event.getType(), ActorDTO._toConvertFromActorEntity(event.getActor()),
                RepoDTO._toConvertFromRepoEntity(event.getRepo()), DateUtils.getCreatedAtAsFormatString(event));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActorDTO getActor() {
        return actor;
    }

    public void setActor(ActorDTO actor) {
        this.actor = actor;
    }

    public RepoDTO getRepo() {
        return repo;
    }

    public void setRepo(RepoDTO repo) {
        this.repo = repo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
