package com.breyes.peliculaskotlin.mvvm.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.breyes.peliculaskotlin.R
import com.breyes.peliculaskotlin.data.model.Movie
import com.breyes.peliculaskotlin.mvvm.main.movies.popular.PopularFragment
import com.breyes.peliculaskotlin.mvvm.main.movies.top.TopFragment
import com.breyes.peliculaskotlin.mvvm.main.search.SearchFragment
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.view.KeyEvent.KEYCODE_BACK
import com.breyes.peliculaskotlin.base.BaseActivity
import com.breyes.peliculaskotlin.util.replaceFragmentInActivity


class MainMoviesActivity : BaseActivity(), MainItemUserActionListener {

    private lateinit var mToolbar: Toolbar

    override fun onMovieClicked(movie: Movie) {
        Toast.makeText(applicationContext, movie.original_title, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_movies_activity)
        setupFragment()

    }

    fun popFragment(view: View) {
        this.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KEYCODE_BACK))
        this.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KEYCODE_BACK))
        hideInputMethod()
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.movies_frame_popular)
        PopularFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.movies_frame_popular)
        }

        supportFragmentManager.findFragmentById(R.id.movies_frame_top)
        TopFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.movies_frame_top)
        }
    }

    fun hideInputMethod() {

        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    fun showInputMethod() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun onSearchViewClicked(view: View) {
        addFragmentOnTop(SearchFragment.newInstance().apply {}, R.id.search_frame)
    }

    fun addFragmentOnTop(fragment: Fragment, layout: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(layout, fragment)
                .addToBackStack("something")
                .commit()
    }
}
