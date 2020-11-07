package com.trevorthlam.codingtest.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoInfo(
        val id: Int,
        val name: String,
        val owner: RepoOwner,
        val url: String,
        val created_at: String,
        val pushed_at: String,
        val language: String?,
        val forks_count: Int,
        val open_issues_count: Int
)