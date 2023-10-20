package com.example.ruuttest.presentation.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.data.datas.AuthState
import com.example.ruuttest.databinding.ActivityLoginBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.viewModels.LoginViewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var email: String
    private lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configViewModels()
        configClicks()
        inputsObserver() //to observe responses from api
    }

    private fun inputsObserver() {
        loginViewModel.authStates.observe(this) {
            when (it) {
                is AuthState.Success -> {
                    onSuccessLogIn()
                }

                is AuthState.AuthInputError -> {
                    showErrorInput(it)
                }

                is AuthState.AuthError -> {
                    it.message?.let { msg -> showErrorDialog(msg, 0, "") }
                }

                else -> {}
            }
        }
    }

    private fun onSuccessLogIn() {
        binding.etEmail.text?.clear()
        binding.etPassword.text?.clear()
        goStartActivity(MainActivity::class.java)
    }

    private fun showErrorInput(authState: AuthState.AuthInputError) {
        with(binding){
            if (authState.tag == "email") {
                if (authState.message == 0) {
                    tilEmail.error = null
                } else {
                    tilEmail.run {
                        error = getString(authState.message)
                        requestFocus()
                    }
                }
            }
            if (authState.tag == "pass") {
                if (authState.message == 0) {
                    tilPassword.error = null
                } else {
                    tilPassword.run {
                        error = getString(authState.message)
                        requestFocus()
                    }
                }
            }
        }
    }

    private fun goStartActivity(cls: Class<*>?) {
        val intent = Intent(this, cls)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        startActivity(intent)
    }

    private fun configClicks() {
        binding.btnLogin.setOnClickListener {
            email = binding.etEmail.text.toString()
            pass = binding.etPassword.text.toString()
            loginViewModel.handleLogin(email, pass, this)
        }

        binding.lnlSignUp.setOnClickListener {
            goStartActivity(SignUpActivity::class.java)
        }
    }

    private fun configViewModels() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }
}