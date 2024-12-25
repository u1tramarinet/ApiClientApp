package io.github.u1tramarinet.apiclientapp.domain.github

import io.github.u1tramarinet.apiclientapp.data.repository.github.MockWebServerGitHubRepoRepositoryImpl

class GetReposMockUseCase {
    suspend operator fun invoke(): List<GitHubRepo> {
        return MockWebServerGitHubRepoRepositoryImpl().getRepos("u1tramarinet")
    }
}