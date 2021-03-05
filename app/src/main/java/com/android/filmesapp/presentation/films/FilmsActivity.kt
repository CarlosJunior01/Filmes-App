package com.android.filmesapp.presentation.films

import OnItemClickListener
import addOnItemClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFilmsBinding
import com.android.filmesapp.presentation.authentication.FormLoginActivity
import com.google.firebase.auth.FirebaseAuth

class FilmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()

        val viewModel: FilmsViewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)

        viewModel.filmesLiveData.observe(this, {
            it?.let { filmsList ->
                with(binding.recyclerViewVertical) {

                    layoutManager = GridLayoutManager(applicationContext, 3)
                    setHasFixedSize(true)
                    adapter = FilmsAdapterListTwo(filmsList) {
                        val intent = FilmsDetails.getStartIntent(this@FilmsActivity, it.id, it.title, it.overview, it.release_date, it.poster_path)
                        this@FilmsActivity.startActivity(intent)
                        Log.i("Test", "#: Click" )
                    }
                }
            }
        })
        viewModel.getFilms()
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
                OpenLoginActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun OpenLoginActivity(){
        var intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
    }

    private fun setRecyclerView() {

        val recyclerFilmsHorizontal= binding.recyclerViewHorizontal
        recyclerFilmsHorizontal.adapter = FilmsAdapterListOne(addFilms())
        recyclerFilmsHorizontal.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        recyclerFilmsHorizontal.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                FilmsDetail(position)
            }
        })
    }

    private fun FilmsDetail(position: Int) {
        val itent = Intent(this, FilmsDetails::class.java)
        itent.putExtra("selectedItem", position)
        startActivity(itent)
    }
}


