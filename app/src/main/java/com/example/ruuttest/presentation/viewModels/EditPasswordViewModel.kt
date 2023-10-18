package com.example.ruuttest.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruuttest.R

class EditPasswordViewModel: ViewModel() {

    val validInputs = MutableLiveData<Boolean>()

    var password = MutableLiveData<String>()
    private var isPasswordError: Int = 0
    val passwordError = MutableLiveData<Int>()

    var confirmPassword = MutableLiveData<String>()
    private var isConfirmPasswordError: Int = 0
    val confirmPasswordError = MutableLiveData<Int>()

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

}