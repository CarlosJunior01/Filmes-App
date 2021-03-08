package com.android.filmesapp.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivitySearchBinding
import com.android.filmesapp.presentation.details.MoviesDetails
import com.android.filmesapp.presentation.favorites.FavoritesActivity
import com.android.filmesapp.presentation.movies.*

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    val viewModel: SearchViewModel by lazy { ViewModelProviders.of(this).get(SearchViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBindings()
        setBottomNavigationView()
        observeViewModel()

    }

    private fun setBindings() {
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
    }

    private fun observeViewModel() {
        viewModel.moviesLiveData.observe(this, {
            it?.let { filmsList ->
                with(binding.recyclerViewVertical) {

                    layoutManager = GridLayoutManager(applicationContext, 3)
                    setHasFixedSize(true)
                    adapter = SearchAdapter(filmsList) {
                        val intent = MoviesDetails.getStartIntent(
                            this@SearchActivity,
                            it.overview,
                            it.poster_path,
                        )
                        this@SearchActivity.startActivity(intent)
                    }
                }
            }
        })
        viewModel.getFilms("")
    }

    private fun setBottomNavigationView() {
        val bottomNavigationView = binding.bottomMenu
        bottomNavigationView.selectedItemId = R.id.nav_search
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> openFilmsActivity()
                R.id.nav_libs -> openFavoritActivity()
                else -> openSearchActivity()
            }
        }
    }

    private fun openFilmsActivity(): Boolean {
        val intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun openFavoritActivity(): Boolean {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun openSearchActivity(): Boolean {
        return true
    }
}