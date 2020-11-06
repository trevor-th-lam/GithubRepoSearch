package com.trevorthlam.codingtest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.trevorthlam.codingtest.R
import com.trevorthlam.codingtest.databinding.FragmentDetailBinding
import com.trevorthlam.codingtest.models.RepoInfo
import com.trevorthlam.codingtest.models.SearchResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var url: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        url = args.repoUrl
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val queue = Volley.newRequestQueue(it)

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
                val repository = Json { ignoreUnknownKeys = true }.decodeFromString<RepoInfo>(response.toString())
                println("Repository: %s".format(repository))
                binding.repo = repository

            }, {
                println("Response: Failed")
            })

            queue.add(jsonObjectRequest)
        }
    }
}