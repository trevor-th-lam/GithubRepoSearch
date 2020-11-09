package com.trevorthlam.githubRepoSearch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trevorthlam.githubRepoSearch.services.GithubRepository

class ViewModelFactory(private val repository: GithubRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchRepoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}