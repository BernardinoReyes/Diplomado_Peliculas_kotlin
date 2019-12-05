package com.breyes.peliculaskotlin.util

import android.content.Context
import android.preference.PreferenceManager
import com.breyes.peliculaskotlin.data.source.MoviesRepository
import com.breyes.peliculaskotlin.data.source.remote.MoviesRemoteDataSource
import com.breyes.peliculaskotlin.data.source.local.MoviesLocalDataSource

object Injection {

    fun provideMoviesRepository(mContext: Context) =
            MoviesRepository.getInstance(MoviesRemoteDataSource,
                    MoviesLocalDataSource.getInstance(PreferenceManager.getDefaultSharedPreferences(mContext)))
}