package com.trevorthlam.codingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.trevorthlam.codingtest.adapters.RepoAdapter
import com.trevorthlam.codingtest.models.RepoInfo
import com.trevorthlam.codingtest.databinding.FragmentMainBinding
import com.trevorthlam.codingtest.interfaces.RepoDelegate
import com.trevorthlam.codingtest.models.SearchResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainFragment : Fragment(), RepoDelegate {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerViewAdapter: RepoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAdapter = RepoAdapter(this)
        binding.recyclerView.adapter = recyclerViewAdapter

    }

    fun onSearch(view: View) {
        binding.editTextSearch.hint = "OK!"

        context?.let {
            val queue = Volley.newRequestQueue(it)
            val url = "https://api.github.com/search/repositories?q=tetris&page=1&per_page=5"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->

                val repository = Json { ignoreUnknownKeys = true }.decodeFromString<SearchResult>(response.toString())
                println("Repository: %s".format(repository))
                recyclerViewAdapter.submitList(repository.items)

            }, {
                println("Response: Failed")
            })

            queue.add(jsonObjectRequest)
        }
    }

    fun onSearch(view: View) {
        binding.editTextSearch.hint = "OK!"
    }
}