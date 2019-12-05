package com.breyes.peliculaskotlin.data.source.local

import android.content.SharedPreferences
import android.support.annotation.VisibleForTesting
import com.breyes.peliculaskotlin.data.source.MoviesDataSource
import com.breyes.peliculaskotlin.util.helper.Preference

class MoviesLocalDataSource private constructor(private val preferences: SharedPreferences) : MoviesDataSource {
    override fun getCasts(callback: MoviesDataSource.GetCastsCallback, movieId: String) {

    }

    override fun getDetail(callback: MoviesDataSource.GetDetailCallback, movieId: String) {

    }

    override fun getSearch(callback: MoviesDataSource.GetMoviesCallback, query: String) {

    }

    override fun getNowPlaying(callback: MoviesDataSource.GetMoviesCallback) {

    }

    override fun getTopRated(callback: MoviesDataSource.GetMoviesCallback) {

    }

    override fun saveMovieId(movieId: String) {
        preferences.edit().putString(Preference.KEY, movieId)
    }

    override fun getMovieId(): String = preferences.getString(Preference.KEY, "")

    override fun getPopular(callback: MoviesDataSource.GetMoviesCallback) {

    }

    companion object {

        private var INSTANCE: MoviesLocalDataSource? = null

        @JvmStatic
        fun getInstance(preferences: SharedPreferences): MoviesLocalDataSource {
            if (INSTANCE == null) {
                synchronized(MoviesLocalDataSource::javaClass) {
                    INSTANCE = MoviesLocalDataSource(preferences)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}