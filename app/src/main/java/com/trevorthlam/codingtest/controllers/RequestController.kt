package com.trevorthlam.codingtest.controllers

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.trevorthlam.codingtest.interfaces.RequestDelegate
import com.trevorthlam.codingtest.models.Repo
import com.trevorthlam.codingtest.models.RepoSearchResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RequestController(private val context: Context, private val delegate: RequestDelegate) {

    private val resultPerPage = 30

    fun searchRepo(keyword: String) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.github.com/search/repositories?q=${keyword}&page=1&per_page=${resultPerPage}"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            val repository = Json { ignoreUnknownKeys = true }.decodeFromString<RepoSearchResult>(response.toString())
            println("Repository: %s".format(repository))
            delegate.didRetrieveSearchResult(repository)
        }, { error ->
            println("Error: ${error.message}")
        })

        queue.add(jsonObjectRequest)
    }


    //  TODO: Placeholder for ReadMe
    fun getRepoInfo(repo: Repo) {
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, repo.url, null, { response ->
            delegate.didRetrieveRepo(response.toString())
        }, { error ->
            println("Error: ${error.message}")
        })

        queue.add(jsonObjectRequest)
    }
}