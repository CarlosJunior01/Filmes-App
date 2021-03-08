package com.android.filmesapp.presentation.details

import OnItemClickListener
import addOnItemClickListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.android.filmesapp.R
import com.android.filmesapp.data.model.addFilms
import com.android.filmesapp.databinding.ActivityFilmsDetailsBinding
import com.android.filmesapp.presentation.movies.*
import com.android.filmesapp.presentation.player.VideoActivity
import com.bumptech.glide.Glide

class MoviesDetails : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsDetailsBinding
    private val viewModel: DetailsViewModel by lazy { ViewModelProviders.of(this).get(DetailsViewModel::class.java)}
    private val description by lazy { intent.getStringExtra("DESCRIPTION")}
    private val poster by lazy { intent.getStringExtra("POSTER")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenMode()
        setBindings()
        setMovieCover()
        observeViewModel()
        setRecyclerView()

    }

    private fun setBindings() {
        binding.txtDescription.text = description

        binding.imgBackArrow.setOnClickListener {
            openFilmsActivity()
        }

        binding.imgStar.setOnClickListener {
            binding.imgStar.setImageResource(R.drawable.ic_star)
        }
    }

    private fun setMovieCover() {
        var itemSelected: Int? = null
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getInt("selectedItem")
            itemSelected = value
        }

        binding.imgPlay.setOnClickListener {
            if (itemSelected != null) {
                openVideoActivity(itemSelected)
            }
        }

        if (poster != null) {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500/${poster}")
                .into(binding.imgCover)
        } else viewModel.getCover(itemSelected)
    }

    private fun observeViewModel() {
        viewModel.mCoverPosition.observe(this, {
            it?.let { coverPosition ->
                Glide.with(applicationContext)
                    .load(coverPosition)
                    .into(binding.imgCover)
            }
        })
    }

    private fun setRecyclerView() {
        val recyclerFilmsVertical = binding.recyclerViewVertical
        recyclerFilmsVertical.adapter = FilmsAdapterList(addFilms())
        recyclerFilmsVertical.layoutManager = GridLayoutManager(applicationContext, 3)

        recyclerFilmsVertical.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                filmsDetail(position)
            }
        })
    }

    private fun filmsDetail(position: Int) {
        val itent = Intent(this, MoviesDetails::class.java)
        itent.putExtra("selectedItem", position);
        startActivity(itent)
    }

    private fun openFilmsActivity() {
        val intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }

    private fun openVideoActivity(position: Int) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("selectedItem", position);
        startActivity(intent)
    }

    private fun screenMode() {
        supportActionBar!!.hide()
    }

    companion object {
        fun getStartIntent(
            context: Context, description: String, poster: String
        ): Intent {
            return Intent(context, MoviesDetails::class.java).apply {
                putExtra("DESCRIPTION", description)
                putExtra("POSTER", poster)
            }
        }
    }
}