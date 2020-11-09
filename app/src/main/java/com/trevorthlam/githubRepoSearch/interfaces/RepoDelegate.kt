package com.trevorthlam.githubRepoSearch.interfaces

import com.trevorthlam.githubRepoSearch.models.Repo

interface RepoDelegate {
    fun onClick(repo: Repo)
}