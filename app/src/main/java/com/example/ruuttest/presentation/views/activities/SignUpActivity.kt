package com.example.ruuttest.presentation.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.data.datas.AuthState
import com.example.ruuttest.databinding.ActivitySignUpBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.viewModels.SignUpViewModel

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var signUpViewModel: SignUpViewModel

    private lateinit var email: String
    private lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configClicks()
        configViewModels()
    }

    private fun configClicks() {
        binding.btnSignUp.setOnClickListener {
            email = binding.etEmail.text.toString()
            pass = binding.etPassword.text.toString()
            val confirmPass = binding.etConfirmPassword.text.toString()
            signUpViewModel.handleSignUp(email, pass, confirmPass, this)
            signUpViewModel.authStates.observe(this) {
                when (it) {
                    is AuthState.Success -> {
                        onSuccessSign()
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

        binding.lnlLogin.setOnClickListener {
            goStartActivity(LoginActivity::class.java)
        }
    }

    private fun onSuccessSign() {
        binding.etEmail.text?.clear()
        binding.etPassword.text?.clear()
        binding.etConfirmPassword.text?.clear()

        Toast.makeText(this@SignUpActivity, "User created successfully!!", Toast.LENGTH_SHORT)
            .show()

        goStartActivity(LoginActivity::class.java)
    }

    private fun showErrorInput(authState: AuthState.AuthInputError) {
        with(binding) {
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
            if (authState.tag == "passConf") {
                if (authState.message == 0) {
                    tilConfirmPassword.error = null
                } else {
                    tilConfirmPassword.run {
                        error = getString(authState.message)
                        requestFocus()
                    }
                }
            }

        }
    }

    private fun goStartActivity(cls: Class<*>?) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }

    private fun configViewModels() {
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
    }
}