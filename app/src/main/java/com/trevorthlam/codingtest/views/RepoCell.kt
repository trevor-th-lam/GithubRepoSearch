package com.trevorthlam.codingtest.views

import androidx.recyclerview.widget.RecyclerView
import com.trevorthlam.codingtest.databinding.CellRepoBinding
import com.trevorthlam.codingtest.models.Repo

class RepoCell(val binding: CellRepoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Repo) {
        binding.repo = item
    }

}