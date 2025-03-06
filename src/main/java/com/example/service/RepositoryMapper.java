package com.example.service;

import com.example.dto.GithubBranchDto;
import com.example.dto.GithubRepositoryDto;
import com.example.model.GithubBranchResponse;
import com.example.model.GithubRepositoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RepositoryMapper {

    @Mapping(target = "repositoryName", source = "repo.name")
    @Mapping(target = "ownerLogin", source = "repo.owner.login")
    @Mapping(target = "branches", source = "branches")
    GithubRepositoryResponse toDomain(GithubRepositoryDto repo, List<GithubBranchDto> branches);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastCommitSha", source = "commit.sha")
    GithubBranchResponse toBranchInfo(GithubBranchDto branch);

    default List<GithubBranchResponse> mapBranches(List<GithubBranchDto> branches) {
        return branches.stream()
                .map(this::toBranchInfo)
                .toList();
    }
}
