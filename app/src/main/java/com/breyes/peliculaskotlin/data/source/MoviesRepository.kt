package com.breyes.peliculaskotlin.data.source

import com.breyes.peliculaskotlin.data.model.Casts
import com.breyes.peliculaskotlin.data.model.Detail
import com.breyes.peliculaskotlin.data.model.Movie

open class MoviesRepository(val remoteDataSource: MoviesDataSource, val localDataSource: MoviesDataSource) : MoviesDataSource {
    override fun getCasts(callback: MoviesDataSource.GetCastsCallback, movieId: String) {
        remoteDataSource.getCasts(object : MoviesDataSource.GetCastsCallback {
            override fun onCastsLoaded(casts: Casts) {
                callback.onCastsLoaded(casts)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }

        }, movieId)
    }

    override fun getDetail(callback: MoviesDataSource.GetDetailCallback, movieId: String) {
        remoteDataSource.getDetail(object : MoviesDataSource.GetDetailCallback {
            override fun onDetailLoaded(detail: Detail?) {
                callback.onDetailLoaded(detail)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }

        }, movieId)
    }

    override fun getSearch(callback: MoviesDataSource.GetMoviesCallback, query: String) {
        remoteDataSource.getSearch(object : MoviesDataSource.GetMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                callback.onMoviesLoaded(movies)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }
        }, query)
    }

    override fun getPopular(callback: MoviesDataSource.GetMoviesCallback) {
        remoteDataSource.getPopular(object : MoviesDataSource.GetMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                callback.onMoviesLoaded(movies)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }
        })
    }

    override fun getNowPlaying(callback: MoviesDataSource.GetMoviesCallback) {
        remoteDataSource.getNowPlaying(object : MoviesDataSource.GetMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                callback.onMoviesLoaded(movies)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }
        })
    }

    override fun getTopRated(callback: MoviesDataSource.GetMoviesCallback) {
        remoteDataSource.getTopRated(object : MoviesDataSource.GetMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                callback.onMoviesLoaded(movies)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }
        })
    }

    override fun saveMovieId(movieId: String) {
        remoteDataSource.saveMovieId(movieId)
        localDataSource.saveMovieId(movieId)
    }

    override fun getMovieId(): String = localDataSource.getMovieId()

    companion object {

        private var INSTANCE: MoviesRepository? = null

        @JvmStatic
        fun getInstance(mMoviesRemoteDataSource: MoviesDataSource, mMoviesLocalDataSource: MoviesDataSource) =
                INSTANCE ?: synchronized(MoviesRepository::class.java) {
                    INSTANCE ?: MoviesRepository(mMoviesRemoteDataSource, mMoviesLocalDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}