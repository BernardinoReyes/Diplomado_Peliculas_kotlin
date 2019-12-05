package com.breyes.peliculaskotlin.base

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment

open class BaseFragment : Fragment() {

    private var mDialogProgress: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    fun showProgressDialog(isShowDialog: Boolean) {

        if (isShowDialog) {
            mDialogProgress?.show()
        } else {
            mDialogProgress?.dismiss()
        }
    }
}