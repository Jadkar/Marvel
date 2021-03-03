package com.globant.openbankassignment.ui.base

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment


open class BaseFragment: Fragment() {

   protected fun showAlertMessage(title:String,message:String){
       AlertDialog.Builder(context)
           .setTitle(title)
           .setMessage(message) // Specifying a listener allows you to take an action before dismissing the dialog.
           // The dialog is automatically dismissed when a dialog button is clicked.
           .setPositiveButton(R.string.ok,
               DialogInterface.OnClickListener { dialog, which ->
                   // Continue with delete operation
                   dialog.dismiss()
               }) // A null listener allows the button to dismiss the dialog and take no further action.
           .show()
   }
}