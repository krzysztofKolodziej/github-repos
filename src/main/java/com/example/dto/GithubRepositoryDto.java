package com.example.dto;

public record GithubRepositoryDto(
        String name,
        Boolean fork,
        Owner owner
) {
    public record Owner(String login) {}
}
