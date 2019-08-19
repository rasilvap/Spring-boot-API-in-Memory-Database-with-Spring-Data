package com.hackerrank.github.services;

import com.hackerrank.github.dto.RepoDTO;
import com.hackerrank.github.model.Repo;

public interface RepoService {
    Repo createRepo(RepoDTO actorDTO);
}
