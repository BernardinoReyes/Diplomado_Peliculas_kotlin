package com.breyes.peliculaskotlin.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.breyes.peliculaskotlin.data.source.MoviesRepository
import com.breyes.peliculaskotlin.mvvm.detail.DetailViewModel
import com.breyes.peliculaskotlin.mvvm.detail.casts.CastsViewModel
import com.breyes.peliculaskotlin.mvvm.detail.info.InfoViewModel
import com.breyes.peliculaskotlin.mvvm.main.movies.popular.PopularViewModel
import com.breyes.peliculaskotlin.mvvm.main.movies.top.TopViewModel
import com.breyes.peliculaskotlin.mvvm.main.search.SearchViewModel

class ViewModelFactory private constructor(
        private val mApplication: Application,
        private val mMoviesRepository: MoviesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(PopularViewModel::class.java) ->
                        PopularViewModel(mApplication, mMoviesRepository)
                    isAssignableFrom(TopViewModel::class.java) ->
                        TopViewModel(mApplication, mMoviesRepository)
                    isAssignableFrom(SearchViewModel::class.java) ->
                        SearchViewModel(mApplication, mMoviesRepository)
                    isAssignableFrom(DetailViewModel::class.java) ->
                        DetailViewModel(mApplication, mMoviesRepository)
                    isAssignableFrom(InfoViewModel::class.java) ->
                        InfoViewModel(mApplication, mMoviesRepository)
                    isAssignableFrom(CastsViewModel::class.java) ->
                        CastsViewModel(mApplication, mMoviesRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(mApplication,
                            Injection.provideMoviesRepository(mApplication.applicationContext))
                            .also { INSTANCE = it }
                }
    }
}