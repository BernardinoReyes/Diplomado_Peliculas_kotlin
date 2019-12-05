package com.breyes.peliculaskotlin.mvvm.detail.info

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.breyes.peliculaskotlin.data.model.Detail
import com.breyes.peliculaskotlin.data.model.Movie
import com.breyes.peliculaskotlin.data.source.MoviesDataSource
import com.breyes.peliculaskotlin.data.source.MoviesRepository

class InfoViewModel(context: Application, private val mMoviesRepository: MoviesRepository) : AndroidViewModel(context) {


    @SuppressLint("StaticFieldLeak")
    var mContext = context

    var backdrop_path = ObservableField<String>()
    var budget = ObservableField<String>()
    var overview = ObservableField<String>()
    var revenue = ObservableField<String>()
    var runtime = ObservableField<String>()
    var vote_average = ObservableField<String>()
    var vote_count = ObservableField<String>()

    fun start(mMovie: Movie) {
        getInfo(mMovie)
    }

    fun getInfo(mMovie: Movie) {
        mMoviesRepository.getDetail(object : MoviesDataSource.GetDetailCallback {
            override fun onDetailLoaded(detail: Detail?) {

            }

            override fun onDataNotAvailable() {
            }

            override fun onError(errorMessage: String?) {
                if (errorMessage != null) {
                }
            }

        }, mMovie.id.toString())
    }

}


