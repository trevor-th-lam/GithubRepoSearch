package com.trevorthlam.codingtest.interfaces

import com.trevorthlam.codingtest.models.RepoInfo

interface RepoDelegate {
    fun onClick(repo: RepoInfo)
}