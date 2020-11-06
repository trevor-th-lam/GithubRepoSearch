package com.trevorthlam.codingtest.views

import androidx.recyclerview.widget.RecyclerView
import com.trevorthlam.codingtest.databinding.CellRepoBinding
import com.trevorthlam.codingtest.models.RepoInfo

class RepoCell(val binding: CellRepoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RepoInfo) {
        binding.repo = item
    }

}