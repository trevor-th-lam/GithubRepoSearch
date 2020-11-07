package com.trevorthlam.codingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trevorthlam.codingtest.controllers.RequestController
import com.trevorthlam.codingtest.adapters.RepoAdapter
import com.trevorthlam.codingtest.databinding.FragmentMainBinding
import com.trevorthlam.codingtest.interfaces.RepoDelegate
import com.trevorthlam.codingtest.interfaces.RequestDelegate
import com.trevorthlam.codingtest.models.RepoInfo
import com.trevorthlam.codingtest.models.SearchResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainFragment : Fragment(), RepoDelegate, RequestDelegate {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerViewAdapter: RepoAdapter
    private lateinit var requestController: RequestController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAdapter = RepoAdapter(this)
        binding.recyclerView.adapter = recyclerViewAdapter
        context?.let {
            requestController = RequestController(it, this)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onSearch(view: View) {
        if(binding.editTextSearch.text.isNotBlank()) {
            requestController.searchRepo(binding.editTextSearch.text.toString())
        } else {
            context?.let {
                Toast.makeText(it, "Please input keyword", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(repo: RepoInfo) {
//        requestController.getRepoInfo(repo)

        val json = Json.encodeToString(repo)
        val action = MainFragmentDirections.actionMainToDetail(json)
        findNavController().navigate(action)
    }

    override fun didRetrieveSearchResult(searchResult: SearchResult) {
        recyclerViewAdapter.submitList(searchResult.items)
    }

    override fun didRetrieveRepo(json: String) {
        //  TODO: Placeholder for ReadMe
//        val action = MainFragmentDirections.actionMainToDetail(json)
//        findNavController().navigate(action)
    }
}