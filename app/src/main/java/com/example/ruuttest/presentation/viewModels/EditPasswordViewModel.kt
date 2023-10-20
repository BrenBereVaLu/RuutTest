package com.example.ruuttest.presentation.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruuttest.R
import com.example.ruuttest.data.databases.AppDatabase
import com.example.ruuttest.data.databases.User
import com.example.ruuttest.data.datas.DataState
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditPasswordViewModel: ViewModel() {


    private var usuario: User? = null
    private var user: FirebaseUser? = null
    val validInputs = MutableLiveData<Boolean>()

    var password = MutableLiveData<String>()
    private var isPasswordError: Int = 0
    val passwordError = MutableLiveData<Int>()

    var confirmPassword = MutableLiveData<String>()
    private var isConfirmPasswordError: Int = 0
    val confirmPasswordError = MutableLiveData<Int>()

    private val _dataState by lazy { MutableLiveData<DataState>(DataState.Idle) }
    val dataState: MutableLiveData<DataState> = _dataState

    private var email: String? = null
    private var passw: String? = null

    private lateinit var appDb: AppDatabase

    fun attemptInputPass(password: String, confirmPass: String) {
        validInputs.value = validateFields(password, confirmPass)
        if(!validInputs.value!!){
            passwordError.value = isPasswordError
            confirmPasswordError.value = isConfirmPasswordError
        }
    }

    private fun validateFields(password: String, confirmPass:String): Boolean {
        var isValid = true

        if(password.isEmpty()){
            isValid = false
            isPasswordError = R.string.text_password_obligatory
        }else if(password.trim().length < 8){
            isValid = false
            isPasswordError = R.string.text_password_length
        }else if(password.isBlank()){
            isValid = false
            isPasswordError = R.string.text_password_obligatory
        }else{
            isPasswordError = 0
        }

        if(confirmPass.isEmpty()){
            isValid = false
            isConfirmPasswordError = R.string.text_confirm_password_obligatory
        }else if(confirmPass.trim().length < 8){
            isValid = false
            isConfirmPasswordError = R.string.text_password_length
        }else if(confirmPass.isBlank()){
            isValid = false
            isConfirmPasswordError = R.string.text_confirm_password_obligatory
        }else if(password != confirmPass){
            isValid = false
            isConfirmPasswordError = R.string.text_password_same
        }else{
            isConfirmPasswordError = 0
        }

        return isValid
    }

    fun handleShowData(context: Context) {
        user = FirebaseAuth.getInstance().currentUser
        appDb = AppDatabase.getDatabase(context)
        CoroutineScope(Dispatchers.IO).launch {
            usuario = user?.email?.let { appDb.userDao().findByEmail(it) }
            email = usuario?.email
            passw = usuario?.password
            withContext(Dispatchers.Main){
                _dataState.value = DataState.DataUser(user, usuario)
            }
        }
    }

    fun handleUpdateData(pass: String, context: Context) {
        user = FirebaseAuth.getInstance().currentUser
        appDb = AppDatabase.getDatabase(context)
        user!!.updatePassword(pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                CoroutineScope(Dispatchers.IO).launch {
                    user?.email.let {
                        if (it != null) {
                            appDb.userDao().update(pass, it)
                        }
                    }
                    withContext(Dispatchers.Main){
                        _dataState.value = DataState.DataUpdateUserSuccess
                    }
                }
            } else {
                task.exception?.let {
                    Log.i("TAG", "Email signup failed with error ${it.localizedMessage}")
                    _dataState.value = DataState.DataUserError(it.localizedMessage)
                }
            }
        }
    }

    fun handleDeleteData(context: Context){
        user = FirebaseAuth.getInstance().currentUser
        appDb = AppDatabase.getDatabase(context)

        val authCredential =
            passw?.let { EmailAuthProvider.getCredential(email!!, it) }

        if (authCredential != null) {
            user?.reauthenticate(authCredential)?.addOnCompleteListener {
                user!!.delete().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        CoroutineScope(Dispatchers.IO).launch{
                            appDb.userDao().deleteAll()
                            withContext(Dispatchers.Main){
                                signOutSession(false)
                                _dataState.value = DataState.DataDeleteUserSuccess
                            }
                        }

                    }else {
                        task.exception?.let {
                            Log.i("TAG", "Email signup failed with error ${it.localizedMessage}")
                            _dataState.value = DataState.DataUserError(it.localizedMessage)
                        }
                    }
                }
            }
        }

    }

     fun signOutSession(onClick: Boolean) {
        FirebaseAuth.getInstance().signOut()
         if(onClick){
             _dataState.value = DataState.DataSignOut
         }
    }

}