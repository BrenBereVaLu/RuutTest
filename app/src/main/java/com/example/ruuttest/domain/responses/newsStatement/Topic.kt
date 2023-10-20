package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Topic(
    @SerializedName("relevance_score")
    
    var relevanceScore: String,
    @SerializedName("topic")
    
    var topic: String
)