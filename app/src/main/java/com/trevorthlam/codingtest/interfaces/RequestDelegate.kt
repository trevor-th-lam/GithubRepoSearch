package com.trevorthlam.codingtest.interfaces

import com.trevorthlam.codingtest.models.RepoSearchResult

interface RequestDelegate {
    fun didRetrieveSearchResult(repoSearchResult: RepoSearchResult)
    fun didRetrieveRepo(json: String)
}