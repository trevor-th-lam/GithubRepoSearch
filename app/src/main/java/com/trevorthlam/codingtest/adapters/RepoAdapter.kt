package com.trevorthlam.codingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.trevorthlam.codingtest.R
import com.trevorthlam.codingtest.databinding.CellRepoBinding
import com.trevorthlam.codingtest.interfaces.RepoDelegate
import com.trevorthlam.codingtest.models.Repo
import com.trevorthlam.codingtest.views.RepoCell

class RepoAdapter(val delegate: RepoDelegate): ListAdapter<Repo, RepoCell>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCell {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CellRepoBinding>(layoutInflater, R.layout.cell_repo, parent, false)
        binding.delegate = delegate
        return RepoCell(binding)
    }

    override fun onBindViewHolder(holder: RepoCell, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
        }
    }
}