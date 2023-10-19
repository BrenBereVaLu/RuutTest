package com.example.ruuttest.domain.responses.errors


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

/**
 * I don't understand because you use "Expose" decorator.
 */
data class ErrorMessageResponse(
    @SerializedName("Error Message")
    @Expose
    var errorMessage: String
)