package com.android.filmesapp.presentation.films

import OnItemClickListener
import addOnItemClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFilmsDetailsBinding
import com.bumptech.glide.Glide

class FilmsDetails : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenMode()
        setRecyclerView()

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

        val cover = CoversStorage()
        var coverPosition = cover.cover_01

        when(itemSelected){
            0 -> coverPosition = cover.cover_01
            1 -> coverPosition = cover.cover_02
            2 -> coverPosition = cover.cover_03
            3 -> coverPosition = cover.cover_04
            4 -> coverPosition = cover.cover_05
            5 -> coverPosition = cover.cover_06
            6 -> coverPosition = cover.cover_07
            7 -> coverPosition = cover.cover_08
            8 -> coverPosition = cover.cover_09
            9 -> coverPosition = cover.cover_10
            10 -> coverPosition = cover.cover_11
            11 -> coverPosition = cover.cover_12
        }

        binding.imgPlay.setOnClickListener {
            if (itemSelected != null) {
                OpenVideoActivity(itemSelected)
            }
        }

        Glide.with(applicationContext)
            .load(coverPosition)
            .centerCrop()
            .into(binding.imgCover)
    }

    private fun setRecyclerView() {

        val recyclerFilmsVertical = binding.recyclerViewVertical
        recyclerFilmsVertical.adapter = FilmsAdapter(addFilms())
        recyclerFilmsVertical.layoutManager = GridLayoutManager(applicationContext, 3)

        recyclerFilmsVertical.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
               // FilmsDetail()
            }
        })
    }

//    private fun FilmsDetail() {
//        val itent = Intent(this, FilmsDetails::class.java)
//        startActivity(itent)
//    }

    private fun OpenFilmsActivity() {
        var intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }

    private fun OpenVideoActivity(position: Int) {
        var intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("selectedItem", position);
        startActivity(intent)
    }

    private fun screenMode() {
        supportActionBar!!.hide()
    }
}