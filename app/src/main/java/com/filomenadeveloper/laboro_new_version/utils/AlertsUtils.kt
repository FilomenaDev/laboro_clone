@file:Suppress("DEPRECATION")

package com.filomenadeveloper.laboro_new_version.utils

import android.app.ProgressDialog
import android.content.Context

object ProgressDialogUtil {

    private lateinit var progressDialog: ProgressDialog

    fun show(context: Context, message: String) {
        progressDialog = ProgressDialog(context)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun hide() {
        progressDialog.dismiss()
    }
}


