package com.trevorthlam.codingtest.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoOwner(
        val login: String
)