package com.example.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class GithubResourceIT {

    /**
     * Comprehensive happy path test verifying:
     * 1. Successful response for existing user
     * 2. Response data structure validity
     * 3. Fork filtering functionality
     * 4. Technical data correctness (commit SHAs)
     */
    @Test
    void getRepositories_HappyPath_ReturnsNonForkReposWithBranches() {
        given()
                .when().get("/github/spring-projects")
                .then()
                // Basic response validation
                .statusCode(200)
                .body("", hasSize(greaterThan(0)))

                // Repository structure checks
                .body("repositoryName", everyItem(not(emptyString())))
                .body("ownerLogin", everyItem(equalTo("spring-projects")))

                // Business logic verification - fork filtering
                .body("repositoryName", hasItem("spring-boot")) // Known non-fork repo
                .body("repositoryName", not(hasItem("spring-framework"))) // Known fork

                // Branch data validation
                .body("branches", everyItem(hasSize(greaterThan(0))))
                .body("branches.name.flatten()", everyItem(not(emptyString())))
                .body("branches.lastCommitSha.flatten()",
                        everyItem(matchesPattern("^[a-f0-9]{40}$"))); // SHA-1 format
    }
}
