package com.trevorthlam.codingtest.interfaces

import com.trevorthlam.codingtest.models.Repo

interface RepoDelegate {
    fun onClick(repo: Repo)
}