package com.example.ruuttest.presentation.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruuttest.R
import com.example.ruuttest.data.databases.AppDatabase
import com.example.ruuttest.data.databases.User
import com.example.ruuttest.data.datas.AuthState
import com.example.ruuttest.presentation.views.activities.SignUpActivity
import com.example.ruuttest.utils.CommonUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _authState by lazy { MutableLiveData<AuthState>(AuthState.Idle) }
    val authStates: MutableLiveData<AuthState> = _authState

    private lateinit var appDb: AppDatabase

    private fun validateFields(email: String, password: String, confirmPass:String): Boolean {
        var isValid = true

        if(email.isEmpty()){
            isValid = false
            _authState.value = AuthState.AuthInputError("email",R.string.text_email_obligatory)
        }else if(!CommonUtils.isEmailValid(email)){
            isValid = false
            _authState.value = AuthState.AuthInputError("email",R.string.text_email_obligatory)
        }else if(email.isBlank()){
            isValid = false
            _authState.value = AuthState.AuthInputError("email",R.string.text_email_obligatory)
        }else{
            _authState.value = AuthState.AuthInputError("email",0)
        }

        if(password.isEmpty()){
            isValid = false
            _authState.value = AuthState.AuthInputError("pass",R.string.text_password_obligatory)
        }else if(password.trim().length < 8){
            isValid = false
            _authState.value = AuthState.AuthInputError("pass",R.string.text_password_length)
        }else if(password.isBlank()){
            isValid = false
            _authState.value = AuthState.AuthInputError("pass",R.string.text_password_obligatory)
        }else{
            _authState.value = AuthState.AuthInputError("pass",0)
        }

        if(confirmPass.isEmpty()){
            isValid = false
            _authState.value = AuthState.AuthInputError("passConf",R.string.text_confirm_password_obligatory)
        }else if(confirmPass.trim().length < 8){
            isValid = false
            _authState.value = AuthState.AuthInputError("passConf",R.string.text_password_length)
        }else if(confirmPass.isBlank()){
            isValid = false
            _authState.value = AuthState.AuthInputError("passConf",R.string.text_confirm_password_obligatory)
        }else if(password != confirmPass){
            isValid = false
            _authState.value = AuthState.AuthInputError("passConf",R.string.text_password_same)
        }else{
            _authState.value = AuthState.AuthInputError("passConf",0)
        }

        return isValid
    }

    fun handleSignUp(
        email: String,
        password: String,
        confirmPassword: String,
        context: Context
    ) {
        if(validateFields(email, password, confirmPassword)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("TAG","Email signup is successful")
                        appDb = AppDatabase.getDatabase(context)
                        val user = User(null, email, password)
                        CoroutineScope(Dispatchers.IO).launch {
                            appDb.userDao().insert(user)
                        }
                        _authState.value = AuthState.Success

                    } else {
                        task.exception?.let {
                            Log.i("TAG","Email signup failed with error ${it.localizedMessage}")
                            _authState.value = AuthState.AuthError(it.localizedMessage)
                        }
                    }
                }
        }

    }

}