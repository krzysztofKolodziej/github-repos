package com.example.resource;

import com.example.model.GithubRepositoryResponse;
import com.example.service.GithubService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/github")
public class GithubResource {

    private final GithubService githubService;

    public GithubResource(GithubService githubService) {
        this.githubService = githubService;
    }

    @GET
    @Path("/{username}")
    public Uni<List<GithubRepositoryResponse>> getRepositories(@PathParam("username") String username) {
        return githubService.getNonForkRepositories(username);
    }

}
