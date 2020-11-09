package com.trevorthlam.githubRepoSearch.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RepoOwner(
        @SerializedName("login")  val name: String
)