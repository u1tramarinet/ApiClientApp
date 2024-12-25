package io.github.u1tramarinet.apiclientapp.data.repository.github

import io.github.u1tramarinet.apiclientapp.domain.github.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class MockWebServerGitHubRepoRepositoryImpl : GitHubRepoRepository {
    private val mockServer = MockWebServer()

    override suspend fun getRepos(userName: String): List<GitHubRepo> {
        return withContext(Dispatchers.IO) {
            mockServer.start()
            val retrofit = setupRetrofit()
            mockServer.enqueue(setupResponse())
            val service = retrofit.create(GitHubApiService::class.java)
            val response = service.getRepositories(userName)
            mockServer.shutdown()
            response
        }
    }

    private fun setupRetrofit(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockServer.url(""))
            .build()
    }

    private fun setupResponse(): MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                    [
                    {
                        "id": 1,
                        "name": "repo1",
                        "html_url": "https://example.com/repo1"
                    },
                    {
                        "id": 2,
                        "name": "repo2",
                        "html_url": "https://example.com/repo2"
                    }
                ]
                """.trimIndent()
            )
    }
}