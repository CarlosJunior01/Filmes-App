package com.android.filmesapp.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.filmesapp.databinding.RecyclerItemFilmBinding
import com.bumptech.glide.Glide

class FilmsAdapterListOne(val films: MutableList<Films>): RecyclerView.Adapter<FilmsAdapterListOne.FilmsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapterListOne.FilmsViewHolder {
        val binding = RecyclerItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsAdapterListOne.FilmsViewHolder, position: Int) {
        with(holder){
            with(films[position]){
                //binding.filmCover.setImageResource(covers)
                Glide.with(itemView)
                    .load(covers)
                    .centerCrop()
                    .into(binding.filmCover)
            }
        }
    }

    override fun getItemCount() = films.size

    inner class FilmsViewHolder( val binding: RecyclerItemFilmBinding) : RecyclerView.ViewHolder(binding.root)
}