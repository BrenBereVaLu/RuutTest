package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

/**
 * I don't understand because you use "Expose" decorator.
 */
data class Topic(
    @SerializedName("relevance_score")
    @Expose
    var relevanceScore: String,
    @SerializedName("topic")
    @Expose
    var topic: String
)