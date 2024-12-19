package io.github.u1tramarinet.apiclientapp.data.repository.github

import io.github.u1tramarinet.apiclientapp.domain.github.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class GitHubRepoRepositoryImpl : GitHubRepoRepository {
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://api.github.com/")
        .build()
    private val service = retrofit.create(GitHubApiService::class.java)

    override suspend fun getRepos(userName: String): List<GitHubRepo> {
        return withContext(Dispatchers.IO) {
            service.getRepositories(userName)
        }
    }
}