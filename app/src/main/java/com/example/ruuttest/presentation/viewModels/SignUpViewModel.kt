package com.example.ruuttest.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruuttest.R
import com.example.ruuttest.utils.CommonUtils

class SignUpViewModel : ViewModel() {

    private var isPasswordError: Int = 0
    private var isConfirmPasswordError: Int = 0
    private var isEmailError: Int = 0
    val gotoLoginScreen = MutableLiveData<Boolean>()
    val passwordError = MutableLiveData<Int>()
    val confirmPasswordError = MutableLiveData<Int>()
    val emailError = MutableLiveData<Int>()

    fun attemptSignUp(email: String, password: String, confirmPass: String) {
        gotoLoginScreen.value = validateFields(email, password, confirmPass)
        if(!gotoLoginScreen.value!!){
            passwordError.value = isPasswordError
            emailError.value = isEmailError
            confirmPasswordError.value = isConfirmPasswordError
        }
    }

    private fun validateFields(email: String, password: String, confirmPass:String): Boolean {
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
}