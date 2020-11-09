package com.trevorthlam.githubRepoSearch.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoOwner(
        val login: String
)