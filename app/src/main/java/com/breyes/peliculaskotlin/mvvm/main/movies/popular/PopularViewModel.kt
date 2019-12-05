package com.breyes.peliculaskotlin.mvvm.main.movies.popular

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.breyes.peliculaskotlin.data.model.Movie
import com.breyes.peliculaskotlin.data.source.MoviesDataSource
import com.breyes.peliculaskotlin.data.source.MoviesRepository

class PopularViewModel(context: Application, private val mMoviesRepository: MoviesRepository) : AndroidViewModel(context) {

    val movieList: ObservableList<Movie> = ObservableArrayList()

    @SuppressLint("StaticFieldLeak")
    var mContext = context

    fun start() {
        getPopular()
    }

    fun getPopular() {
        mMoviesRepository.getPopular(object : MoviesDataSource.GetMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                with(movieList) {
                    clear()
                    addAll(movies!!)
                }
            }

            override fun onDataNotAvailable() {
            }

            override fun onError(errorMessage: String?) {
                if (errorMessage != null) {
                }
            }

        })
    }
}


