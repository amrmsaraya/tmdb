package com.github.amrmsaraya.tmdb.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.amrmsaraya.tmdb.R
import com.github.amrmsaraya.tmdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationBar()
    }

    private fun initBottomNavigationBar() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val menu = binding.bottomNavigationView.menu
            when (it.itemId) {
                R.id.movies -> {
                    it.setIcon(R.drawable.ic_baseline_movie_24)
                    menu.findItem(R.id.artists).setIcon(R.drawable.ic_outline_person_24)
                    menu.findItem(R.id.favorites).setIcon(R.drawable.ic_baseline_favorite_border_24)
                }
                R.id.artists -> {
                    menu.findItem(R.id.movies).setIcon(R.drawable.ic_outline_movie_24)
                    it.setIcon(R.drawable.ic_baseline_person_24)
                    menu.findItem(R.id.favorites).setIcon(R.drawable.ic_baseline_favorite_border_24)
                }
                R.id.favorites -> {
                    menu.findItem(R.id.movies).setIcon(R.drawable.ic_outline_movie_24)
                    menu.findItem(R.id.artists).setIcon(R.drawable.ic_outline_person_24)
                    it.setIcon(R.drawable.ic_baseline_favorite_24)
                }
                R.id.tv -> {
                    menu.findItem(R.id.movies).setIcon(R.drawable.ic_outline_movie_24)
                    menu.findItem(R.id.artists).setIcon(R.drawable.ic_outline_person_24)
                    menu.findItem(R.id.favorites).setIcon(R.drawable.ic_baseline_favorite_border_24)
                }
                R.id.search -> {
                    menu.findItem(R.id.movies).setIcon(R.drawable.ic_outline_movie_24)
                    menu.findItem(R.id.artists).setIcon(R.drawable.ic_outline_person_24)
                    menu.findItem(R.id.favorites).setIcon(R.drawable.ic_baseline_favorite_border_24)
                }
            }
            true
        }
    }
}
