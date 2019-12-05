package com.breyes.peliculaskotlin.mvvm.detail

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.breyes.peliculaskotlin.data.source.MoviesRepository

class DetailViewModel(context: Application, private val mMoviesRepository: MoviesRepository) : AndroidViewModel(context) {

    @SuppressLint("StaticFieldLeak")
    var mContext = context
}


