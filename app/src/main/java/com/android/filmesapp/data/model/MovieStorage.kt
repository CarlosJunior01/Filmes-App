package com.android.filmesapp.data.model

data class MovieStorage (val covers: String)

class FilmsBuilder{
    var covers: String = ""
    fun build(): MovieStorage = MovieStorage(covers)
}

fun films(block: FilmsBuilder.() -> Unit): MovieStorage = FilmsBuilder().apply(block).build()

fun addFilms(): MutableList<MovieStorage> = mutableListOf(
    films { covers = FirebaseManager.cover_01 },
    films { covers = FirebaseManager.cover_02 },
    films { covers = FirebaseManager.cover_03 },
    films { covers = FirebaseManager.cover_04 },
    films { covers = FirebaseManager.cover_05 },
    films { covers = FirebaseManager.cover_06 },
    films { covers = FirebaseManager.cover_07 },
    films { covers = FirebaseManager.cover_08 },
    films { covers = FirebaseManager.cover_09 },
    films { covers = FirebaseManager.cover_10 },
    films { covers = FirebaseManager.cover_11 },
    films { covers = FirebaseManager.cover_12 },
)



