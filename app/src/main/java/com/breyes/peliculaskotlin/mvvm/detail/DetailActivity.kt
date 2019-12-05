package com.breyes.peliculaskotlin.mvvm.detail

import android.os.Bundle
import com.breyes.peliculaskotlin.R
import com.breyes.peliculaskotlin.base.BaseActivity
import com.breyes.peliculaskotlin.data.model.Movie
import com.breyes.peliculaskotlin.util.replaceFragmentInActivity

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setupFragment()

    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.detail_header_frame)
        DetailFragment.newInstance(intent.getSerializableExtra(getString(R.string.detail_intent)) as Movie).let {
            replaceFragmentInActivity(it, R.id.detail_header_frame)
        }
    }
}
