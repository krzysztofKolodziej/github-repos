package com.example.dto;

public record GithubRepositoryDto(
        String name,
        boolean fork,
        Owner owner
) {
    public record Owner(String login) {}
}
