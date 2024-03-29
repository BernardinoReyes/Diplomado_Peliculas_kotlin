package com.breyes.peliculaskotlin.mvvm.main.movies.popular

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.breyes.peliculaskotlin.base.BaseFragment
import com.breyes.peliculaskotlin.databinding.PopularFragmentBinding
import com.breyes.peliculaskotlin.util.obtainViewModel
import kotlinx.android.synthetic.main.popular_fragment.*

class PopularFragment : BaseFragment() {

    private lateinit var mViewDataBinding: PopularFragmentBinding
    private lateinit var mAdapter: PopularAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewDataBinding = PopularFragmentBinding.inflate(inflater, container, false).apply {
            mViewModel = obtainViewModel()
        }

        return mViewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewDataBinding.mViewModel?.start()
        setupMovies()
    }

    override fun onResume() {
        super.onResume()

        showProgressDialog(true)

        showProgressDialog(false)
    }

    private fun obtainViewModel(): PopularViewModel = obtainViewModel(PopularViewModel::class.java)

    private fun setupMovies() {
        val mViewModel = mViewDataBinding.mViewModel

        if (mViewModel != null) {

            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL

            mAdapter = PopularAdapter(mViewModel.movieList, mViewModel, context)

            with(recyclerViewMain) {
                adapter = mAdapter
                setLayoutManager(layoutManager)
            }
        }
    }

    companion object {

        fun newInstance() = PopularFragment().apply {

        }
    }
}