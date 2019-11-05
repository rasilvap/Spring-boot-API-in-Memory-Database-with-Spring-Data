package com.events.business.services;

import com.events.business.dto.RepoDTO;
import com.events.business.model.Repo;

public interface RepoService {
    Repo createRepo(RepoDTO actorDTO);
}
