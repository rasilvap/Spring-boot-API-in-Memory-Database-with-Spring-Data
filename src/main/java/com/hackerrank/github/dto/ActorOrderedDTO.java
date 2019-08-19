package com.hackerrank.github.dto;

import com.hackerrank.github.model.Actor;
import java.sql.Timestamp;

public class ActorOrderedDTO {

    Actor actor;
    Integer cEvents;
    Timestamp last;

    public ActorOrderedDTO() {}

    public ActorOrderedDTO(Actor actor, Integer cEvents, Timestamp last) {
        this.actor = actor;
        this.cEvents = cEvents;
        this.last = last;
    }

    @Override
    public String toString() {
        return "ActorOrderedDTO{" +
                "actor=" + actor +
                ", cEvents=" + cEvents +
                ", last=" + last +
                '}';
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Integer getcEvents() {
        return cEvents;
    }

    public void setcEvents(Integer cEvents) {
        this.cEvents = cEvents;
    }

    public Timestamp getLast() {
        return last;
    }

    public void setLast(Timestamp last) {
        this.last = last;
    }
}
