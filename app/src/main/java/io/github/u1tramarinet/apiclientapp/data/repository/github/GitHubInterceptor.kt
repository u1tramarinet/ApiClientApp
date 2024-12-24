package io.github.u1tramarinet.apiclientapp.data.repository.github

import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val builder = originRequest.newBuilder()
        builder.addHeader("User-Agent", "ApiClientApp")
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}