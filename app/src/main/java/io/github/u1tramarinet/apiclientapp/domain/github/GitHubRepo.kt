package io.github.u1tramarinet.apiclientapp.domain.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepo(
    val id: Int,
    val name: String,
    @SerialName(value = "html_url")
    val url: String,
)