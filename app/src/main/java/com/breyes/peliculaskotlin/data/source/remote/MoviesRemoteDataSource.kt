package com.breyes.peliculaskotlin.data.source.remote

import com.breyes.peliculaskotlin.data.source.MoviesDataSource
import com.breyes.peliculaskotlin.util.helper.Network.API_KEY
import com.breyes.peliculaskotlin.util.helper.Network.LANGUAGE_ENGLISH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MoviesRemoteDataSource : MoviesDataSource {

    private val mApiService = MoviesService.create()
    private lateinit var mMovieId: String

    override fun getDetail(callback: MoviesDataSource.GetDetailCallback, movieId: String) {
        mApiService.getDetail(
                movieId,
                API_KEY
        )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results != null) {
                            callback.onDetailLoaded(results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }

    override fun getCasts(callback: MoviesDataSource.GetCastsCallback, movieId: String) {
        mApiService.getCasts(
                movieId,
                API_KEY
        )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results != null) {
                            callback.onCastsLoaded(results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }


    override fun getPopular(callback: MoviesDataSource.GetMoviesCallback) {
        mApiService.getPopular(
                API_KEY,
                LANGUAGE_ENGLISH,
                "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.results!!.isNotEmpty()) {
                            callback.onMoviesLoaded(results?.results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }

    override fun getNowPlaying(callback: MoviesDataSource.GetMoviesCallback) {
        mApiService.getNowPlaying(
                API_KEY,
                LANGUAGE_ENGLISH,
                "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.results!!.isNotEmpty()) {
                            callback.onMoviesLoaded(results?.results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })

    }

    override fun getTopRated(callback: MoviesDataSource.GetMoviesCallback) {
        mApiService.getTopRated(
                API_KEY,
                LANGUAGE_ENGLISH,
                "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.results!!.isNotEmpty()) {
                            callback.onMoviesLoaded(results?.results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }

    override fun getSearch(callback: MoviesDataSource.GetMoviesCallback, query: String) {
        mApiService.getSearch(
                API_KEY,
                LANGUAGE_ENGLISH,
                query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.results!!.isNotEmpty()) {
                            callback.onMoviesLoaded(results.results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }


    override fun saveMovieId(movieId: String) {
        mMovieId = movieId
    }

    override fun getMovieId(): String {
        return mMovieId
    }


}