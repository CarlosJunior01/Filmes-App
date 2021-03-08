package com.android.filmesapp.presentation.movies

import OnItemClickListener
import addOnItemClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.filmesapp.R
import com.android.filmesapp.data.model.addFilms
import com.android.filmesapp.databinding.ActivityFilmsBinding
import com.android.filmesapp.presentation.authentication.FormLoginActivity
import com.android.filmesapp.presentation.details.MoviesDetails
import com.android.filmesapp.presentation.error.GenericErrorActivity
import com.android.filmesapp.presentation.favorites.FavoritesActivity
import com.android.filmesapp.presentation.search.SearchActivity
import com.google.firebase.auth.FirebaseAuth

class FilmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsBinding

    lateinit var layoutManager: LinearLayoutManager
    private val viewModelFactory = MoviesViewModelFactory()
    private val viewModel: FilmsViewModel by lazy { ViewModelProvider(this, viewModelFactory).get(
            FilmsViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsBinding.inflate(layoutInflater)
        layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)

        setBottomNavigationView()
        observeViewModel()
        setRecyclerView()

    }

    private fun observeViewModel() {
        viewModel.mutableLiveData.observe(this, {
            it?.let { filmsList ->
                with(binding.recyclerViewVertical) {
                    layoutManager = GridLayoutManager(this@FilmsActivity, 3)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(filmsList) { movies ->
                        val intent = MoviesDetails.getStartIntent(
                            this@FilmsActivity,
                            movies.overview, movies.poster_path
                        )
                        adapter?.notifyDataSetChanged()
                        this@FilmsActivity.startActivity(intent)
                    }
                }
            }
        })
        viewModel.errorLiveData.observe(this, { isError ->
            isError?.let { if (isError == true) openGenericEror() }
        })
        viewModel.getFilms()
    }

    private fun setRecyclerView() {

        val recyclerFilmsHorizontal = binding.recyclerViewHorizontal
        recyclerFilmsHorizontal.adapter = FilmsAdapterList(addFilms())
        recyclerFilmsHorizontal.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        recyclerFilmsHorizontal.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                filmsDetail(position)
            }
        })
    }

    private fun setBottomNavigationView() {
        val bottomNavigationView = binding.bottomMenu
        bottomNavigationView.selectedItemId = R.id.nav_home
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_search -> openSearchActivity()
                R.id.nav_libs -> openFavoritActivity()
                else -> openFilmsActivity()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signout -> {
                FirebaseAuth.getInstance().signOut()
                openLoginActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openLoginActivity() {
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
    }

    private fun filmsDetail(position: Int) {
        val itent = Intent(this, MoviesDetails::class.java)
        itent.putExtra("selectedItem", position)
        startActivity(itent)
    }

    private fun openSearchActivity(): Boolean {
        val itent = Intent(this, SearchActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun openFavoritActivity(): Boolean {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun openFilmsActivity(): Boolean {
        return true
    }

    private fun openGenericEror() {
        val intent = Intent(this, GenericErrorActivity::class.java)
        startActivity(intent)
    }
}


