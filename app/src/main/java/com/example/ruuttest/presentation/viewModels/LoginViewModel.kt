package com.example.ruuttest.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruuttest.R
import com.example.ruuttest.utils.CommonUtils

class LoginViewModel : ViewModel() {

    private var isPasswordError: Int = 0
    private var isEmailError: Int = 0
    val gotoMainScreen = MutableLiveData<Boolean>()
    val passwordError = MutableLiveData<Int>()
    val emailError = MutableLiveData<Int>()

    fun attemptLogin(email: String, password: String){
        gotoMainScreen.value = validateFields(email, password)
        if(!gotoMainScreen.value!!){
            passwordError.value = isPasswordError
            emailError.value = isEmailError
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        var isValid = true

        if(email.isEmpty()){
            isValid = false
            isEmailError = R.string.text_email_obligatory
        }else if(!CommonUtils.isEmailValid(email)){
            isValid = false
            isEmailError = R.string.text_email_invalid
        }else if(email.isBlank()){
            isValid = false
            isEmailError = R.string.text_email_obligatory
        }else{
            isEmailError = 0
        }

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

        return isValid
    }

}