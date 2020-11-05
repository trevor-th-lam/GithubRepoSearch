package com.trevorthlam.codingtest

import kotlinx.serialization.Serializable

@Serializable
data class RepoOwner(
        val login: String
)