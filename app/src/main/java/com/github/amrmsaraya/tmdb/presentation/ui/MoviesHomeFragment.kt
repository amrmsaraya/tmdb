package com.github.amrmsaraya.tmdb.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.amrmsaraya.tmdb.databinding.FragmentMoviesHomeBinding


class MoviesHomeFragment : Fragment() {
    private lateinit var binding: FragmentMoviesHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
