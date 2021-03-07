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

class FilmsDetails : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenMode()
        setRecyclerView()

        val description = intent.getStringExtra("DESCRIPTION")
        val poster = intent.getStringExtra("POSTER")


        binding.txtDescription.setText(description)

        binding.imgBackArrow.setOnClickListener {
            OpenFilmsActivity()
        }

        binding.imgStar.setOnClickListener {
            binding.imgStar.setImageResource(R.drawable.ic_star)
        }

        var itemSelected: Int? = null
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getInt("selectedItem")
            itemSelected = value
        }

        val viewModel: DetailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        viewModel.mCoverPosition.observe(this, {
            it?.let { coverPosition ->
                Glide.with(applicationContext)
                    .load(coverPosition)
                    .into(binding.imgCover)
            }
        })

        binding.imgPlay.setOnClickListener {
            if (itemSelected != null) {
                OpenVideoActivity(itemSelected)
            }
        }

        if (poster != null) {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500/${poster}")
                .into(binding.imgCover)
        } else viewModel.getCover(itemSelected)
    }

    private fun setRecyclerView() {

        val recyclerFilmsVertical = binding.recyclerViewVertical
        recyclerFilmsVertical.adapter = FilmsAdapterList(addFilms())
        recyclerFilmsVertical.layoutManager = GridLayoutManager(applicationContext, 3)

        recyclerFilmsVertical.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                FilmsDetail(position)
            }
        })
    }

    private fun FilmsDetail(position: Int) {
        val itent = Intent(this, FilmsDetails::class.java)
        itent.putExtra("selectedItem", position);
        startActivity(itent)
    }

    private fun OpenFilmsActivity() {
        val intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }

    private fun OpenVideoActivity(position: Int) {
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
            return Intent(context, FilmsDetails::class.java).apply {
                putExtra("DESCRIPTION", description)
                putExtra("POSTER", poster)
            }
        }
    }
}