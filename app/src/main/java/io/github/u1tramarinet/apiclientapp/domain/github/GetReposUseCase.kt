package io.github.u1tramarinet.apiclientapp.domain.github

import io.github.u1tramarinet.apiclientapp.data.repository.github.GitHubRepoRepositoryImpl

class GetReposUseCase {
    suspend operator fun invoke(): List<GitHubRepo> {
        return GitHubRepoRepositoryImpl().getRepos("u1tramarinet")
    }
}