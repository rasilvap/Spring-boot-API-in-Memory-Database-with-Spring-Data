package com.events.business.services;

import com.events.business.repository.RepoRepository;
import com.events.business.dto.RepoDTO;
import com.events.business.model.Repo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RepoServiceImp implements RepoService {

    private final RepoRepository repoRepository;

    public RepoServiceImp(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    @Override
    public Repo createRepo(RepoDTO repoDTO) {
        Repo repo = new Repo();
        BeanUtils.copyProperties(repoDTO, repo);
        return repoRepository.save(repo);
    }
}
