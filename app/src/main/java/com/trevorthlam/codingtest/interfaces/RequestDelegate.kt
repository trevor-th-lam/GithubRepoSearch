package com.trevorthlam.codingtest.interfaces

import com.trevorthlam.codingtest.models.SearchResult

interface RequestDelegate {
    fun didRetrieveSearchResult(searchResult: SearchResult)
    fun didRetrieveRepo(json: String)
}