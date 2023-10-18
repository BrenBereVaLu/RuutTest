package com.example.ruuttest.presentation.bases

import android.app.Dialog
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.example.ruuttest.R
import com.example.ruuttest.utils.CommonUtils
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogAnimation
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import com.thecode.aestheticdialogs.OnDialogClickListener

open class BaseActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog

    open fun showSuccessDialog(message: String, close: Int, tag: String) {
        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.SUCCESS)
            .setTitle(getString(R.string.app_name))
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(true)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    //actions...
                }
            })
            .show()
    }

    open fun showErrorDialog(message: String, close: Int, tag: String){
        AestheticDialog.Builder(this, DialogStyle.FLAT, DialogType.ERROR)
            .setTitle(getString(R.string.app_name))
            .setMessage(message)
            .setCancelable(false)
            .setDarkMode(true)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    //actions...
                }
            })
            .show()
    }

    open fun showLoading() {
        dialog = CommonUtils.showLoadingDialog(this,layoutInflater.inflate(
            R.layout.base_progress_dialog,
            null
        ))
    }

    open fun hideLoading() {
        try {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        } catch (e: IllegalArgumentException) {
            // Handle or log or ignore
            dialog.dismiss()
        } catch (e: Exception) {
            dialog.cancel()
        } finally {
            dialog.cancel()
        }
    }
}