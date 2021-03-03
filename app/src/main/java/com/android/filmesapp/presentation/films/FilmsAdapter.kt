package com.android.filmesapp.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.filmesapp.databinding.RecyclerItemFilmBinding

class FilmsAdapter(val films: MutableList<Films>): RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapter.FilmsViewHolder {
        val binding = RecyclerItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsAdapter.FilmsViewHolder, position: Int) {
        with(holder){
            with(films[position]){
                binding.filmCover.setImageResource(covers)
            }
        }
    }

    override fun getItemCount() = films.size


    inner class FilmsViewHolder( val binding: RecyclerItemFilmBinding) : RecyclerView.ViewHolder(binding.root){

    }

}