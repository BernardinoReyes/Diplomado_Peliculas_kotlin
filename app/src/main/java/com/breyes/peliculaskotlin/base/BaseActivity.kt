package com.breyes.peliculaskotlin.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    internal lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mActivity = this
    }
}
