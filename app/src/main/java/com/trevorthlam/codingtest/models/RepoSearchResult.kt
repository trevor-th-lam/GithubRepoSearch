package com.trevorthlam.codingtest.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoSearchResult(
        val items: List<Repo>
)