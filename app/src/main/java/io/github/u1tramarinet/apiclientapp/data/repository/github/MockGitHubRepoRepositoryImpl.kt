package io.github.u1tramarinet.apiclientapp.data.repository.github

import io.github.u1tramarinet.apiclientapp.domain.github.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class MockGitHubRepoRepositoryImpl : GitHubRepoRepository {
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://api.github.com/")
        .build()
    private val behavior = NetworkBehavior.create()
    private val mockRetrofit = MockRetrofit.Builder(retrofit)
        .networkBehavior(behavior)
        .build()
    private val delegate = mockRetrofit.create(GitHubApiService::class.java)
    private val service = MockGitHubApiService(delegate)

    override suspend fun getRepos(userName: String): List<GitHubRepo> {
        return withContext(Dispatchers.IO) {
            service.getRepositories(userName)
        }
    }
}