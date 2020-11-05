package com.trevorthlam.codingtest

import kotlinx.serialization.Serializable

@Serializable
data class RepoData(
        val name: String,
        val owner: RepoOwner,
        val url: String
)