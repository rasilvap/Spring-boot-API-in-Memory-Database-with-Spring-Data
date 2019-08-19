package com.hackerrank.github.dto;

import com.hackerrank.github.model.Repo;

public class RepoDTO {
    private Long id;
    private String name;
    private String url;

    public RepoDTO(){ }

    public RepoDTO(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static RepoDTO _toConvertFromRepoEntity(Repo repo) {
        return new RepoDTO(repo.getId(), repo.getName(), repo.getUrl());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
