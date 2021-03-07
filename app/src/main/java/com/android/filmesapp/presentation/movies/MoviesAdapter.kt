package com.android.filmesapp.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.filmesapp.data.model.Movies
import com.android.filmesapp.databinding.RecyclerItemBinding
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val filmList: List<Movies>,
    val OnItemClickListener: ((movies: Movies) -> Unit)
    ) : RecyclerView.Adapter<MoviesAdapter.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.FilmsViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding, OnItemClickListener)
    }

    override fun onBindViewHolder(viewholder: MoviesAdapter.FilmsViewHolder, position: Int) {
        viewholder.bindView(filmList[position])
    }

    override fun getItemCount() = filmList.count()

    inner class FilmsViewHolder(val binding: RecyclerItemBinding, private val OnItemClickListener: ((movies: Movies) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        private val image = binding.filmCover

        fun bindView(films: Movies) {
            val poster = films.poster_path

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/${poster}")
                .centerCrop()
                .into(binding.filmCover)

            itemView.setOnClickListener {
                OnItemClickListener.invoke(films)
            }
        }
    }
}