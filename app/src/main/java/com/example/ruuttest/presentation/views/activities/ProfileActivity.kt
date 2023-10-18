package com.example.ruuttest.presentation.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.data.databases.AppDatabase
import com.example.ruuttest.data.databases.User
import com.example.ruuttest.databinding.ActivityProfileBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.viewModels.EditPasswordViewModel
import com.example.ruuttest.presentation.views.dialogs.EditPasswordSheet
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var viewModelPass: EditPasswordViewModel

    private lateinit var appDb: AppDatabase
    private lateinit var firebaseAuth: FirebaseAuth

    private var email: String? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        firebaseAuth = FirebaseAuth.getInstance()
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
        val user = FirebaseAuth.getInstance().currentUser
        user!!.updatePassword(pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                GlobalScope.launch(Dispatchers.IO) {
                    email?.let { appDb.userDao().update(pass, it) }
                }
                runOnUiThread {
                    //showErrorDialog("Error Update!!",0,"")
                    val toast = Toast.makeText(applicationContext, "Update Success", Toast.LENGTH_SHORT)
                    toast.show()
                }
            } else {
                showErrorDialog("Error Update!!",0,"")
            }
        }
    }

    private fun configClicks() {
        with(binding){
            lnlChangePassword.setOnClickListener {
                EditPasswordSheet().show(supportFragmentManager,"editPassTag")
            }

            lnlLogOut.setOnClickListener {
                firebaseAuth.signOut()
                startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                finish()
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

    private fun showData() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            name = user.displayName
            email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
            binding.username.text = name ?: "User"
            binding.txtEmailChange.text = email
        }

        readData(email, false)
    }

    private fun deleteAccount() {
        readData(email,true)
    }

    private fun readData(email: String?, deleteAll: Boolean) {
        lateinit var user: User
        GlobalScope.launch(Dispatchers.IO) {
            user = email?.let { appDb.userDao().findByEmail(it) }!!
            displayData(user, deleteAll)
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun displayData(users: User, deleteAll: Boolean) {
        withContext(Dispatchers.Main){
            binding.txtEmailShow.text = users.email+" DB"
        }
        if(deleteAll){
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            val authCredential =
                users.password?.let { EmailAuthProvider.getCredential(users.email!!, it) }
            if (authCredential != null) {
                firebaseUser?.reauthenticate(authCredential)?.addOnCompleteListener {
                    firebaseUser.delete().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            GlobalScope.launch {
                                appDb.userDao().deleteAll()
                            }
                            runOnUiThread {
                                //showErrorDialog("Error Update!!",0,"")
                                val toast = Toast.makeText(applicationContext, "Delete Success", Toast.LENGTH_SHORT)
                                toast.show()
                            }
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun configViewModels() {
        viewModelPass = ViewModelProvider(this)[EditPasswordViewModel::class.java]
    }
}