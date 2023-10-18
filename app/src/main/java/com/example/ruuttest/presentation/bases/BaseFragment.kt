package com.example.ruuttest.presentation.bases

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.ruuttest.R
import com.example.ruuttest.utils.CommonUtils

open class BaseFragment : Fragment()  {

    private var mActivity: BaseActivity? = null
    var mContext: Context? = null
    private lateinit var dialog: Dialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            mActivity = activity
            mContext = context
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    open fun showSuccessDialog(message: String, close: Int, tag: String) {
        if (mActivity != null) {
            mActivity?.showSuccessDialog(message,close,tag)
        }
    }

    open fun showErrorDialog(message: String, close: Int, tag: String) {
        if (mActivity != null) {
            mActivity?.showErrorDialog(message,close,tag)
        }
    }

    open fun showLoading() {
        dialog = CommonUtils.showLoadingDialog(this.context,layoutInflater.inflate(
            R.layout.base_progress_dialog,
            null
        ))
    }

    open fun hideLoading() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}