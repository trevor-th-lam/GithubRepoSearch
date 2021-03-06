package com.trevorthlam.githubRepoSearch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trevorthlam.githubRepoSearch.services.GithubRepository
import com.trevorthlam.githubRepoSearch.models.Repo
import kotlinx.coroutines.flow.Flow

class SearchRepoViewModel (private val repository: GithubRepository) : ViewModel() {

    var currentQuery: String? = null
    var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(query: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (query == currentQuery && lastResult != null) {
            return lastResult
        }
        currentQuery = query
        val newResult = repository.getSearchResultStream(query).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}