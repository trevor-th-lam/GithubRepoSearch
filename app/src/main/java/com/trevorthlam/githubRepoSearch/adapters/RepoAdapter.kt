package com.trevorthlam.githubRepoSearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.trevorthlam.githubRepoSearch.R
import com.trevorthlam.githubRepoSearch.databinding.CellRepoBinding
import com.trevorthlam.githubRepoSearch.interfaces.RepoDelegate
import com.trevorthlam.githubRepoSearch.models.Repo
import com.trevorthlam.githubRepoSearch.views.RepoCell

class RepoAdapter(private val delegate: RepoDelegate) : PagingDataAdapter<Repo, RepoCell>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCell {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CellRepoBinding>(layoutInflater,
            R.layout.cell_repo, parent, false)
        binding.delegate = delegate
        return RepoCell(binding)
    }

    override fun onBindViewHolder(holder: RepoCell, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
        }
    }
}