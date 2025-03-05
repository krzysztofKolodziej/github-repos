package com.example.client;

import com.example.dto.GithubBranchDto;
import com.example.dto.GithubRepositoryDto;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "github-api")
public interface GithubApiClient {

    @GET
    @Path("users/{username}/repos")
    Uni<List<GithubRepositoryDto>> getUserRepositories(@PathParam("username") String username);

    @GET
    @Path("repos/{owner}/{repo}/branches")
    Uni<List<GithubBranchDto>> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
