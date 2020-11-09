package com.trevorthlam.codingtest

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.trevorthlam.codingtest.models.Repo
import com.trevorthlam.codingtest.services.GithubService
import kotlinx.coroutines.flow.Flow

class GithubRepository(private val service: GithubService) {

    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = { GithubPagingSource(service, query) }
        ).flow
    }
}