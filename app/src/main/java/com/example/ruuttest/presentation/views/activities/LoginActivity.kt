package com.example.ruuttest.presentation.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.data.databases.AppDatabase
import com.example.ruuttest.data.databases.User
import com.example.ruuttest.databinding.ActivityLoginBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.viewModels.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var appDb: AppDatabase
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var email: String
    private lateinit var pass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        firebaseAuth = FirebaseAuth.getInstance()

        configViewModels()
        configClicks()
        inputsObserver() //to observe responses from api
    }

    private fun inputsObserver() {
        loginViewModel.gotoMainScreen.observe(this) {
            if (it) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { fb ->
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

    private fun showErrorInput() {
        loginViewModel.emailError.observe(this) { email ->
            if (email==0) {
                binding.tilEmail.error = null
            } else {
                binding.tilEmail.run {
                    error = getString(email)
                    requestFocus()
                }
            }
        }

        loginViewModel.passwordError.observe(this) { pass ->
            if (pass==0) {
                binding.tilPassword.error = null
            } else {
                binding.tilPassword.run {
                    error = getString(pass)
                    requestFocus()
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun writeData() {
        val user = User(null, email, pass)
        GlobalScope.launch(Dispatchers.IO) {
            if(appDb.userDao().getAnyUser() == null ){
                //table is empty
                appDb.userDao().insert(user)
            }
        }
        binding.etEmail.text?.clear()
        binding.etPassword.text?.clear()
        goStartActivity(MainActivity::class.java)
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
            loginViewModel.attemptLogin(email, pass)
        }

        binding.lnlSignUp.setOnClickListener {
            goStartActivity(SignUpActivity::class.java)
        }
    }

    private fun configViewModels() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }
}