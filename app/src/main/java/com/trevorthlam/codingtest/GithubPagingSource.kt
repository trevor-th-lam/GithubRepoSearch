package com.trevorthlam.codingtest

import androidx.paging.PagingSource
import com.trevorthlam.codingtest.models.Repo
import com.trevorthlam.codingtest.services.GithubService

class GithubPagingSource(private val service: GithubService, private val query: String): PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: 1

        val response = service.searchRepos(query, position, 30)
        val repos: List<Repo> = response.items
        return LoadResult.Page(
            data = repos,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (repos.isEmpty()) null else position + 1
        )
    }
}