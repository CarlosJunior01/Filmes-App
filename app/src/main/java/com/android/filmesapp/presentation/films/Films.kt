package com.android.filmesapp.presentation.films

import com.android.filmesapp.R

data class Films (val covers: Int)

class FilmsBuilder{
    var covers: Int = 0
    fun build(): Films = Films(covers)
}

fun films(block: FilmsBuilder.() -> Unit): Films = FilmsBuilder().apply(block).build()

fun addFilms(): MutableList<Films> = mutableListOf(

    films { covers = R.drawable.filme1 },
    films { covers = R.drawable.filme2 },
    films { covers = R.drawable.filme3 },
    films { covers = R.drawable.filme4 },
    films { covers = R.drawable.filme5 },
    films { covers = R.drawable.filme6 },
    films { covers = R.drawable.filme7 },
    films { covers = R.drawable.filme8 },
    films { covers = R.drawable.filme9 },
    films { covers = R.drawable.filme10},
    films { covers = R.drawable.filme11},
    films { covers = R.drawable.filme12},
)


