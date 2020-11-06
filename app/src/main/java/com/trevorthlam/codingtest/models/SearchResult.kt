package com.trevorthlam.codingtest.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
        val items: List<RepoInfo>
)