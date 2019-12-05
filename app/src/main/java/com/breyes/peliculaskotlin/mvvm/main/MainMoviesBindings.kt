package com.breyes.peliculaskotlin.mvvm.main

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.breyes.peliculaskotlin.data.model.Movie
import com.breyes.peliculaskotlin.mvvm.main.movies.popular.PopularAdapter
import com.breyes.peliculaskotlin.mvvm.main.movies.top.TopAdapter

object MainMoviesBindings {

    @BindingAdapter("app:popularList")
    @JvmStatic
    fun setPopularList(recyclerView: RecyclerView, movies: List<Movie>) {

        with(recyclerView.adapter as PopularAdapter) {
            replaceData(movies)
        }
    }

    @BindingAdapter("app:topList")
    @JvmStatic
    fun setTopList(recyclerView: RecyclerView, movies: List<Movie>) {

        with(recyclerView.adapter as TopAdapter) {
            replaceData(movies)
        }
    }
}