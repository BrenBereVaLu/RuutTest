package com.example.ruuttest.utils

import android.R
import android.app.Dialog
import android.content.Context
import android.view.View
import java.util.regex.Matcher
import java.util.regex.Pattern

object CommonUtils {

    fun isEmailValid(email: String?): Boolean {
        val pattern: Pattern
        val emailPattern = ("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        pattern = Pattern.compile(emailPattern)
        val matcher: Matcher = pattern.matcher(email!!)
        return matcher.matches()
    }

    fun showLoadingDialog(context: Context?, inflate: View): Dialog {
        val dialog = Dialog(context!!, R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(inflate)
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }
}