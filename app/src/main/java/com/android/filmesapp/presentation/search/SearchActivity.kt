package com.android.filmesapp.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivitySearchBinding
import com.android.filmesapp.presentation.details.FilmsDetails
import com.android.filmesapp.presentation.favorites.FavoritesActivity
import com.android.filmesapp.presentation.movies.*

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: SearchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        viewModel.filmesLiveData.observe(this, {
            it?.let { filmsList ->
                with(binding.recyclerViewVertical) {

                    layoutManager = GridLayoutManager(applicationContext, 3)
                    setHasFixedSize(true)
                    adapter = SearchAdapter(filmsList) {
                        val intent = FilmsDetails.getStartIntent(
                            this@SearchActivity,
                            it.overview,
                            it.poster_path,
                        )
                        this@SearchActivity.startActivity(intent)
                        Log.i("Test", "#: Click")
                    }
                }
            }
        })
        viewModel.getFilms("")

        binding.searchHeader.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getFilms(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText != "") { viewModel.getFilms(newText)
                } else viewModel.getFilms("")
                return false
            }
        })

        val bottomNavigationView = binding.bottomMenu
        bottomNavigationView.setSelectedItemId(R.id.nav_search)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> OpenFilmsActivity()
                R.id.nav_libs -> OpenFavoritActivity()
                else -> OpenSearchActivity()
            }
        }
    }

    private fun OpenFilmsActivity(): Boolean {
        val itent = Intent(this, FilmsActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun OpenFavoritActivity(): Boolean {
        val itent = Intent(this, FavoritesActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun OpenSearchActivity(): Boolean {
        return true
    }
}