package com.example.model;

public record GithubBranchResponse(
        String name,
        String lastCommitSha
) {
}
