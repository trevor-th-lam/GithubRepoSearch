package com.trevorthlam.codingtest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
        val id: Long,
        @SerialName("full_name") val fullName: String,
        val description: String?,
        val owner: RepoOwner,
        val url: String,
        val created_at: String,
        val pushed_at: String,
        val language: String?,
        val forks_count: Int,
        val open_issues_count: Int
)