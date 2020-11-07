package com.trevorthlam.codingtest

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.trevorthlam.codingtest.interfaces.RequestDelegate
import com.trevorthlam.codingtest.models.RepoInfo
import com.trevorthlam.codingtest.models.SearchResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RequestController(val context: Context, val delegate: RequestDelegate) {

    fun searchRepo(keyword: String) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.github.com/search/repositories?q=${keyword}&page=1&per_page=30"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            val repository = Json { ignoreUnknownKeys = true }.decodeFromString<SearchResult>(response.toString())
            println("Repository: %s".format(repository))
            delegate.didRetrieveSearchResult(repository)
        }, { error ->
            println("Error: ${error.message}")
        })

        queue.add(jsonObjectRequest)
    }


    fun getRepoInfo(repoInfo: RepoInfo) {
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, repoInfo.url, null, { response ->
            delegate.didRetrieveRepo(response.toString())
        }, { error ->
            println("Error: ${error.message}")
        })

        queue.add(jsonObjectRequest)
    }
}