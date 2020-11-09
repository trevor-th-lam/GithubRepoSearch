package com.trevorthlam.githubRepoSearch

import androidx.lifecycle.ViewModelProvider
import com.trevorthlam.githubRepoSearch.services.GithubRepository
import com.trevorthlam.githubRepoSearch.services.GithubService
import com.trevorthlam.githubRepoSearch.viewModels.ViewModelFactory

object Injection {

    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}