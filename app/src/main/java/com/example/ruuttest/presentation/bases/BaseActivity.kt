package com.example.ruuttest.presentation.bases

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ruuttest.R

open class BaseActivity : AppCompatActivity() {

    open fun showSuccessDialog(message: String, close: Int, tag: String) {
       val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val msg : TextView = dialog.findViewById(R.id.txtMessage)
        val btn : Button = dialog.findViewById(R.id.btnOk)

        msg.text = message

        btn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    open fun showErrorDialog(message: String, close: Int, tag: String) {
       val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val msg : TextView = dialog.findViewById(R.id.txtMessage)
        val btn : Button = dialog.findViewById(R.id.btnOk)

        msg.text = message

        btn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}