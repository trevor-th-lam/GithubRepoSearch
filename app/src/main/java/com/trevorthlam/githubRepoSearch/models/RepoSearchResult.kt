package com.trevorthlam.githubRepoSearch.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoSearchResult(
        val items: List<Repo>
)