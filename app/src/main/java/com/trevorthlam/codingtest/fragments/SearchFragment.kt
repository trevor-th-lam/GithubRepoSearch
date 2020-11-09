package com.trevorthlam.codingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.trevorthlam.codingtest.Injection
import com.trevorthlam.codingtest.adapters.RepoAdapter
import com.trevorthlam.codingtest.viewModels.SearchRepoViewModel
import com.trevorthlam.codingtest.databinding.FragmentSearchBinding
import com.trevorthlam.codingtest.interfaces.RepoDelegate
import com.trevorthlam.codingtest.models.Repo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SearchFragment : Fragment(), RepoDelegate {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var adapter: RepoAdapter

    private lateinit var viewModel: SearchRepoViewModel
    private var searchJob: Job? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.handler = this
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(
            SearchRepoViewModel::class.java)

        adapter = RepoAdapter(this)
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onSearch(view: View) {
        binding.editTextSearch.text.trim().let {
            if (it.isNotEmpty()) {
//                requestController.searchRepo(binding.editTextSearch.text.toString())
                search(it.toString())
            } else {
                context?.let { context ->
                    Toast.makeText(context, "Please input keyword", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(repo: Repo) {
        println(repo)
        val json = Json.encodeToString(repo)
        val action = SearchFragmentDirections.actionMainToDetail(json)
        findNavController().navigate(action)
    }
}