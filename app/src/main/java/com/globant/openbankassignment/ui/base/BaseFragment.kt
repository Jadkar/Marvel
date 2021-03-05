package com.globant.openbankassignment.ui.base

import android.R
import android.app.AlertDialog
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

   protected fun showAlertMessage(title:String,message:String){
       AlertDialog.Builder(context)
           .setTitle(title)
           .setMessage(message)
           .setPositiveButton(R.string.ok
           ) { dialog, which ->

               dialog.dismiss()
           }
           .show()
   }
}