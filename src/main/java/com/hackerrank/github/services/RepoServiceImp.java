package com.hackerrank.github.services;

import com.hackerrank.github.dto.RepoDTO;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.repository.RepoRepository;
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
