package com.breyes.peliculaskotlin.mvvm.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

class TabSliderAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragmentList = ArrayList<Fragment>()
    private val nameList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        nameList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return nameList[position]
    }
}