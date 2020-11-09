package com.trevorthlam.codingtest

import androidx.lifecycle.ViewModelProvider
import com.trevorthlam.codingtest.services.GithubRepository
import com.trevorthlam.codingtest.services.GithubService
import com.trevorthlam.codingtest.viewModels.ViewModelFactory

object Injection {

    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}