package com.breyes.peliculaskotlin.mvvm.main.movies.top

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.breyes.peliculaskotlin.data.model.Movie
import com.breyes.peliculaskotlin.data.source.MoviesDataSource
import com.breyes.peliculaskotlin.data.source.MoviesRepository
import id.gits.jasaraharja.util.SingleLiveEvent

class TopViewModel(context: Application, private val mMoviesRepository: MoviesRepository) : AndroidViewModel(context) {

    val movieList: ObservableList<Movie> = ObservableArrayList()
    internal val mOpenMovieDetail = SingleLiveEvent<Movie>()
    @SuppressLint("StaticFieldLeak")
    var mContext = context

    fun start() {
        getTopRated()
    }

    fun getTopRated() {
        mMoviesRepository.getTopRated(object : MoviesDataSource.GetMoviesCallback {
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


