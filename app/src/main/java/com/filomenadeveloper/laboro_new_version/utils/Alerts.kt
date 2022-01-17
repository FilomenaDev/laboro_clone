package com.filomenadeveloper.laboro_new_version.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import com.filomenadeveloper.laboro_new_version.R
import com.tapadoo.alerter.Alerter

fun showAlertTapadoo(
    activity: Activity,
    title: CharSequence,
    description: CharSequence,
    colorBackground: Int
) {
    Alerter.create (activity)
        .setTitle(title)
        .setText(description)
        .setTextAppearance(R.style.TextAppearance_AppCompat_Body1)
        .enableVibration(true)
        .enableIconPulse(true)
        .setBackgroundColorInt(colorBackground)
        .setBackgroundColorRes(colorBackground)
        .setDuration(5000)
        .setIcon(R.mipmap.ic_launcher)
        .show()
}

class AlertDialog(private var iAlert: IAlert) {

    interface IAlert {
        fun onPositive(dialogInterface: DialogInterface)
        fun onCancel(dialogInterface: DialogInterface)
    }

    fun show(
        context: Context,
        title: String,
        message: String,
        txtPositive: String,
        txtNegative: String
    ) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setCancelable(false)
        alertDialog.setIcon(R.drawable.brand)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(txtPositive) { dialog, _ ->
            iAlert.onPositive(dialog)
        }
        alertDialog.setNegativeButton(txtNegative) { dialog, _ ->
            iAlert.onCancel(dialog)
        }
        alertDialog.create()
        alertDialog.show()
    }
}

object Toast {

    private lateinit var toast: Toast

    fun show(context: Context, message: CharSequence) {
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}


class DialogUtil(var iClickDialog: IClickDialog) {

    interface IClickDialog {
        fun onConfirm(data: String?, dialogInterface: DialogInterface)
        fun onCancel(dialogInterface: DialogInterface)
    }

    fun reportDialog(
        context: Context,
        titleDialog: String,
        hintDialog: String,
        btnConfirm: CharSequence,
        btnCancel: CharSequence
    ) {

        val builder = AlertDialog.Builder(context)
     //   val view = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)
      //  val txtSuggest: AppCompatTextView = view.findViewById(R.id.txtSuggest)
       // val edtSuggest: AppCompatEditText = view.findViewById(R.id.edtSuggest)

      //  txtSuggest.text = titleDialog
      //  edtSuggest.hint = hintDialog

      //  builder.setView(view).setPositiveButton(btnConfirm) { dialog: DialogInterface?, _: Int ->
      //      iClickDialog.onConfirm(edtSuggest.text.toString(), dialog!!)

     //   }.setNegativeButton(btnCancel) { dialog: DialogInterface?, _: Int ->
     //       iClickDialog.onCancel(dialog!!)
     //   }
     //   builder.show()
    }
}



