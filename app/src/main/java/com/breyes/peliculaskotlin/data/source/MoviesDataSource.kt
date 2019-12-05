package com.breyes.peliculaskotlin.data.source

import com.breyes.peliculaskotlin.data.model.Casts
import com.breyes.peliculaskotlin.data.model.Detail
import com.breyes.peliculaskotlin.data.model.Movie

interface MoviesDataSource {

    fun saveMovieId(movieId: String)

    fun getMovieId(): String

    fun getPopular(callback: GetMoviesCallback)

    fun getNowPlaying(callback: GetMoviesCallback)

    fun getTopRated(callback: GetMoviesCallback)

    fun getSearch(callback: GetMoviesCallback, query: String)

    fun getDetail(callback: GetDetailCallback, movieId: String)

    fun getCasts(callback: GetCastsCallback, movieId: String)

    interface GetDetailCallback {
        fun onDetailLoaded(detail: Detail?)
        fun onDataNotAvailable()
        fun onError(errorMessage: String?)
    }

    interface GetCastsCallback {
        fun onCastsLoaded(casts: Casts)
        fun onDataNotAvailable()
        fun onError(errorMessage: String?)
    }

    interface GetMoviesCallback {

        fun onMoviesLoaded(movies: List<Movie>?)

        fun onDataNotAvailable()

        fun onError(errorMessage: String?)
    }
}