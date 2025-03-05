package com.example.model;

import java.util.List;

public record GithubRepositoryResponse(
        String repositoryName,
        String ownerLogin,
        List<GithubBranchResponse> branches
) {
}
