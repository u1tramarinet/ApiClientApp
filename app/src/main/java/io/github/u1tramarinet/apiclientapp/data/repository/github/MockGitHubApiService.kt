package io.github.u1tramarinet.apiclientapp.data.repository.github

import io.github.u1tramarinet.apiclientapp.domain.github.GitHubRepo
import retrofit2.mock.BehaviorDelegate

class MockGitHubApiService(private val delegate: BehaviorDelegate<GitHubApiService>) : GitHubApiService {
    override suspend fun getRepositories(userName: String): List<GitHubRepo> {
        val list = emptyList<GitHubRepo>()
        return delegate.returningResponse(list).getRepositories(userName)
    }
}