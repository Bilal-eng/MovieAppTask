package com.teknasyon.movieapptask

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.teknasyon.movieapptask.utils.CommonUtils
import com.teknasyon.movieapptask.utils.NetworkUtils

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

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }
}