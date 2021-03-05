package com.android.filmesapp.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.filmesapp.databinding.RecyclerItemFilmBinding
import com.bumptech.glide.Glide
import com.carlos.marvel.data.network.response.ResultsResponse

class FilmsAdapterListTwo(private val filmList: List<ResultsResponse>, val OnItemClickListener: ((filmsResults: ResultsResponse) -> Unit))
    : RecyclerView.Adapter<FilmsAdapterListTwo.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapterListTwo.FilmsViewHolder {
        val binding = RecyclerItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding, OnItemClickListener)
    }

    override fun onBindViewHolder(viewholder: FilmsAdapterListTwo.FilmsViewHolder, position: Int) {
        viewholder.bindView(filmList[position])
    }

    override fun getItemCount() = filmList.size

    inner class FilmsViewHolder(val binding: RecyclerItemFilmBinding, private val OnItemClickListener: ((filmsResults: ResultsResponse) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        private val image = binding.filmCover

        fun bindView(films: ResultsResponse) {
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