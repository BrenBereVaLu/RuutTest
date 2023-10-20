package com.example.ruuttest.data.datas

sealed class AuthState {
    object Idle : AuthState()
    object Success : AuthState()
    class AuthError(val message: String? = null) : AuthState()
    class AuthInputError(val tag: String, val message: Int) : AuthState()

}