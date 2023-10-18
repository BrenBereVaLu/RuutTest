package com.example.ruuttest.presentation.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.data.databases.AppDatabase
import com.example.ruuttest.data.databases.User
import com.example.ruuttest.databinding.ActivitySignUpBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.viewModels.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var appDb: AppDatabase

    private lateinit var signUpViewModel: SignUpViewModel

    private lateinit var email: String
    private lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        firebaseAuth = FirebaseAuth.getInstance()

        configClicks()
        configViewModels()
    }

    private fun configClicks() {
        binding.btnSignUp.setOnClickListener {
            email = binding.etEmail.text.toString()
            pass = binding.etPassword.text.toString()
            val confirmPass = binding.etConfirmPassword.text.toString()
            signUpViewModel.attemptSignUp(email, pass, confirmPass)

            signUpViewModel.gotoLoginScreen.observe(this) {
                if (it) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { fb ->
                            if (fb.isSuccessful) {
                                writeData()
                            } else {
                                showErrorDialog(fb.exception.toString(), 0, "")
                            }
                        }
                } else {
                    showErrorInput()
                }
            }
        }

        binding.lnlLogin.setOnClickListener {
            goStartActivity(LoginActivity::class.java)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun writeData() {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            val user = User(null, email, pass)
            GlobalScope.launch(Dispatchers.IO) {
                appDb.userDao().insert(user)
            }
            binding.etEmail.text?.clear()
            binding.etPassword.text?.clear()
            binding.etConfirmPassword.text?.clear()

            Toast.makeText(this@SignUpActivity, "User created successfully!!", Toast.LENGTH_SHORT)
                .show()

            //showSuccessDialog("User created successfully!!",0,"")
            goStartActivity(LoginActivity::class.java)
        }else{
            showErrorDialog("Please enter data !!", 0, "")
        }
    }

    private fun showErrorInput() {
        signUpViewModel.emailError.observe(this) { email ->
            if (email == 0) {
                binding.tilEmail.error = null
            } else {
                binding.tilEmail.run {
                    error = getString(email)
                    requestFocus()
                }
            }
        }

        signUpViewModel.passwordError.observe(this) { pass ->
            if (pass == 0) {
                binding.tilPassword.error = null
            } else {
                binding.tilPassword.run {
                    error = getString(pass)
                    requestFocus()
                }
            }
        }

        signUpViewModel.confirmPasswordError.observe(this) { confirm ->
            if (confirm == 0) {
                binding.tilConfirmPassword.error = null
            } else {
                binding.tilConfirmPassword.run {
                    error = getString(confirm)
                    requestFocus()
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