package com.github.amrmsaraya.tmdb.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.amrmsaraya.tmdb.databinding.FragmentArtistBinding

class ArtistFragment : Fragment() {
    private lateinit var binding: FragmentArtistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArtistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
