package com.trevorthlam.codingtest.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoInfo(
        val name: String,
        val owner: RepoOwner,
        val url: String,
        val created_at: String,
        val updated_at: String,
        val language: String,
        val forks_count: Int,
        val open_issues_count: Int
)