package com.trevorthlam.codingtest

import androidx.lifecycle.ViewModelProvider
import com.trevorthlam.codingtest.services.GithubService

object Injection {

    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}