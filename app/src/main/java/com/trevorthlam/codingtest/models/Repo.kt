package com.trevorthlam.codingtest.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
        val id: Long,
        @SerializedName("full_name") val fullName: String,
        val description: String?,
        val owner: RepoOwner,
        val url: String,
        val created_at: String,
        val pushed_at: String,
        val language: String?,
        @SerializedName("forks_count") val forks: Int,
        @SerializedName("open_issues_count") val openIssues: Int
)