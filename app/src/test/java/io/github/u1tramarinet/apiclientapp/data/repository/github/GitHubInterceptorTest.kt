package io.github.u1tramarinet.apiclientapp.data.repository.github

import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class GitHubInterceptorTest {
    val mockServer = MockWebServer()

    @Before
    fun setup() {
        mockServer.start()
    }

    @After
    fun tearDown() {
        mockServer.close()
    }

    @Test
    fun test() = runTest {
        mockServer.enqueue(setupResponse())

        val client = OkHttpClient.Builder()
            .addInterceptor(GitHubInterceptor())
            .build()
        val json = Json { ignoreUnknownKeys = true }
        val retrofit = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
        val api = retrofit.create(GitHubApiService::class.java)
        api.getRepositories("hoge")
        val request = mockServer.takeRequest()
        assert(request.getHeader("User-Agent") == "ApiClientApp")
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