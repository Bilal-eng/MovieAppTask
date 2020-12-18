package com.teknasyon.movieapptask

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.teknasyon.movieapptask.utils.CommonUtils

abstract class BaseActivity : AppCompatActivity() {


    private var mProgressDialog: AlertDialog? = null


    fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            mProgressDialog?.cancel()
        }
    }
}