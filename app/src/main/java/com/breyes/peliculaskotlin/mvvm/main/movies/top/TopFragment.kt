package com.breyes.peliculaskotlin.mvvm.main.movies.top

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.breyes.peliculaskotlin.base.BaseFragment
import com.breyes.peliculaskotlin.databinding.TopFragmentBinding
import com.breyes.peliculaskotlin.util.obtainViewModel
import kotlinx.android.synthetic.main.top_fragment.*

class TopFragment : BaseFragment() {

    private lateinit var mViewDataBinding: TopFragmentBinding
    private lateinit var mAdapter: TopAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewDataBinding = TopFragmentBinding.inflate(inflater, container, false).apply {
            mViewModel = obtainViewModel()
        }

        return mViewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMovies()
    }

    override fun onResume() {
        super.onResume()

        showProgressDialog(true)

        mViewDataBinding.mViewModel?.start()

        showProgressDialog(false)
    }

    private fun setupMovies() {
        val mViewModel = mViewDataBinding.mViewModel

        if (mViewModel != null) {

            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL

            mAdapter = TopAdapter(mViewModel.movieList, mViewModel,context)
            with(recyclerViewMain) {
                adapter = mAdapter
                setLayoutManager(layoutManager)
            }
        }
    }

    companion object {

        fun newInstance() = TopFragment().apply {

        }
    }

    private fun obtainViewModel(): TopViewModel = obtainViewModel(TopViewModel::class.java)
}