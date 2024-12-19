package io.github.u1tramarinet.apiclientapp.data.repository.github

import io.github.u1tramarinet.apiclientapp.domain.github.GitHubRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") userName: String): List<GitHubRepo>
}