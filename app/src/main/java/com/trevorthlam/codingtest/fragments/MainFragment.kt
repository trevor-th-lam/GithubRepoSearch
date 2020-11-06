package com.trevorthlam.codingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.trevorthlam.codingtest.RepoInfo
import com.trevorthlam.codingtest.databinding.FragmentMainBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val queue = Volley.newRequestQueue(it)
            val url = "https://api.github.com/repos/chvin/react-tetris"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
//                println("Response: /n %s".format(response.toString()))

                val repository = Json { ignoreUnknownKeys = true }.decodeFromString<RepoInfo>(response.toString())
                println("Repository: %s".format(repository))

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