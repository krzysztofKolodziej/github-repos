package com.example.service;

import com.example.client.GithubApiClient;
import com.example.dto.GithubRepositoryDto;
import com.example.model.GithubRepositoryResponse;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class GithubService {

    private final GithubApiClient githubClient;
    private final RepositoryMapper repositoryMapper;

    public GithubService(
            @RestClient GithubApiClient githubClient,
            RepositoryMapper repositoryMapper
    ) {
        this.githubClient = githubClient;
        this.repositoryMapper = repositoryMapper;
    }

    public Uni<List<GithubRepositoryResponse>> getNonForkRepositories(String username) {
        return githubClient.getUserRepositories(username)
                .onItem().transform(repos -> repos.stream()
                        .filter(repo -> Boolean.FALSE.equals(repo.fork()))
                        .toList())
                .onItem().transformToMulti(Multi.createFrom()::iterable)
                .onItem().transformToUniAndMerge(this::fetchBranches)
                .collect().asList();
    }
    private Uni<GithubRepositoryResponse> fetchBranches(GithubRepositoryDto repo) {
        return githubClient.getBranches(repo.owner().login(), repo.name())
                .onItem().transform(branches -> repositoryMapper.toDomain(repo, branches));
    }
}

