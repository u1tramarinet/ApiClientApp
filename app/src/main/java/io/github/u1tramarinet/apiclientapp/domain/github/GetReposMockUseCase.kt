package io.github.u1tramarinet.apiclientapp.domain.github

import io.github.u1tramarinet.apiclientapp.data.repository.github.MockGitHubRepoRepositoryImpl

class GetReposMockUseCase {
    suspend operator fun invoke(): List<GitHubRepo> {
        return MockGitHubRepoRepositoryImpl().getRepos("u1tramarinet")
    }
}