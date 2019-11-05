package com.events.business.dto;

import com.events.business.model.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ActorDTO implements Serializable {

    private Long id;
    private String login;
    @JsonProperty(value = "avatar_url")
    private String avatar;

    public ActorDTO() {}

    public ActorDTO(Long id, String login, String avatar) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
    }

    public static ActorDTO _toConvertFromActorEntity(Actor actor){
        return new ActorDTO(actor.getId(), actor.getLogin(),actor.getAvatar()) ;
    }

    @Override
    public String toString() {
        return "ActorDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
