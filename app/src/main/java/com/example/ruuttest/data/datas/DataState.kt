package com.example.ruuttest.data.datas

import com.example.ruuttest.data.databases.User
import com.google.firebase.auth.FirebaseUser

sealed class DataState {
    object Idle : DataState()
    class DataUser(val user: FirebaseUser?, val usuario: User?) : DataState()
    object DataUpdateUserSuccess : DataState()
    class DataUserError(val message: String? = null) : DataState()
    object DataDeleteUserSuccess : DataState()
    object DataSignOut : DataState()
}