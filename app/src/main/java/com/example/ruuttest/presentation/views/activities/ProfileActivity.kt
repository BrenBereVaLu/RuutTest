package com.example.ruuttest.presentation.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.data.datas.DataState
import com.example.ruuttest.databinding.ActivityProfileBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.viewModels.EditPasswordViewModel
import com.example.ruuttest.presentation.views.dialogs.EditPasswordSheet

class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var viewModelPass: EditPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configViewModels()
        showData()
        configClicks()
        inputsObserver() //to observe responses from api
    }

    private fun inputsObserver() {
        viewModelPass.password.observe(this){ pass ->
            if(pass!=null) {
                if(pass.isNotEmpty() || pass.isNotBlank()){
                    updatePassword(pass)
                }
            }
        }
        viewModelPass.confirmPassword.observe(this){ confirmPass ->
            if(confirmPass!=null) {
                if(confirmPass.isNotEmpty() || confirmPass.isNotBlank()){
                    //showSuccessDialog("confirmPass $confirmPass", 0, "")
                }
            }
        }
    }

    private fun updatePassword(pass: String) {
        viewModelPass.handleUpdateData(pass,this)
        viewModelPass.dataState.observe(this){
            when (it) {
                is DataState.DataUpdateUserSuccess -> {
                    showSuccessDialog("Update Success !!",0,"")
                }
                is DataState.DataUserError -> {
                    it.message?.let { msg -> showErrorDialog(msg,0,"") }
                }

                else -> {}
            }
        }
    }

    private fun configClicks() {
        with(binding){
            lnlChangePassword.setOnClickListener {
                EditPasswordSheet().show(supportFragmentManager,"editPassTag")
            }

            lnlLogOut.setOnClickListener {
                viewModelPass.signOutSession(true)
                viewModelPass.dataState.observe(this@ProfileActivity){
                    when (it) {
                        is DataState.DataSignOut -> {
                            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                            finish()
                        }

                        else -> {}
                    }
                }
            }

            lnlDeleteAccount.setOnClickListener {
                deleteAccount()
            }

            btnHome.setOnClickListener {
                finish()
            }

            txtPasswordChange.setOnClickListener {
                EditPasswordSheet().show(supportFragmentManager,"editPassTag")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showData() {
        viewModelPass.handleShowData(this)
        viewModelPass.dataState.observe(this){
            when (it) {
                is DataState.DataUser -> {
                    binding.username.text = it.user?.displayName ?: "User"
                    binding.txtEmailChange.text = it.user?.email
                    binding.txtEmailShow.text = it.usuario?.email+" DB"
                }

                else -> {}
            }
        }
    }

    private fun deleteAccount() {
        viewModelPass.handleDeleteData(this)
        viewModelPass.dataState.observe(this){
            when (it) {
                is DataState.DataDeleteUserSuccess -> {
                    showSuccessDialog("Update Success !!",0,"")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                is DataState.DataUserError -> {
                    it.message?.let { msg -> showErrorDialog(msg,0,"") }
                }

                else -> {}
            }
        }
    }

    private fun configViewModels() {
        viewModelPass = ViewModelProvider(this)[EditPasswordViewModel::class.java]
    }
}