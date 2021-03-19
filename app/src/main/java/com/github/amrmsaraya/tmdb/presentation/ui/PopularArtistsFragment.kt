package com.github.amrmsaraya.tmdb.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.amrmsaraya.tmdb.databinding.FragmentPopularArtistsBinding


class PopularArtistsFragment : Fragment() {

    private lateinit var binding: FragmentPopularArtistsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPopularArtistsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
