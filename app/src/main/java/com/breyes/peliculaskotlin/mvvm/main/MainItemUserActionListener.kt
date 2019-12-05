package com.breyes.peliculaskotlin.mvvm.main

import com.breyes.peliculaskotlin.data.model.Movie

interface MainItemUserActionListener {

    fun onMovieClicked(movie: Movie)

}