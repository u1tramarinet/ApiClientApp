package io.github.u1tramarinet.apiclientapp.data.repository.github

import io.github.u1tramarinet.apiclientapp.domain.github.GitHubRepo

interface GitHubRepoRepository {
    suspend fun getRepos(userName: String): List<GitHubRepo>
}