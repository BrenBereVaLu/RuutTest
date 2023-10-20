package com.example.ruuttest.domain.responses.errors


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ErrorMessageResponse(
    @SerializedName("Error Message")
    
    var errorMessage: String
)