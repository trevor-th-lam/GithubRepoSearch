package com.trevorthlam.codingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.trevorthlam.codingtest.databinding.FragmentDetailBinding
import com.trevorthlam.codingtest.models.Repo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.repo = Json { ignoreUnknownKeys = true }.decodeFromString<Repo>(args.json)
        return binding.root
    }

}