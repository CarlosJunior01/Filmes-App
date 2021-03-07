package com.android.filmesapp.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.filmesapp.data.model.MovieStorage
import com.android.filmesapp.databinding.RecyclerItemBinding
import com.bumptech.glide.Glide

class FilmsAdapterList(val films: MutableList<MovieStorage>): RecyclerView.Adapter<FilmsAdapterList.FilmsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bindView(films[position])
    }

    override fun getItemCount() = films.size

    class FilmsViewHolder( val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(films: MovieStorage){
            Glide.with(itemView)
                .load(films.covers)
                .centerCrop()
                .into(binding.filmCover)
        }
    }
}
