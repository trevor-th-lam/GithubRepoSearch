package com.trevorthlam.githubRepoSearch.views

import androidx.recyclerview.widget.RecyclerView
import com.trevorthlam.githubRepoSearch.databinding.CellRepoBinding
import com.trevorthlam.githubRepoSearch.models.Repo

class RepoCell(val binding: CellRepoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Repo) {
        binding.repo = item
    }
}