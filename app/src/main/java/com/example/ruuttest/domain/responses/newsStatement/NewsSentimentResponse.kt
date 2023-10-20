package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NewsSentimentResponse(
    @SerializedName("feed")
    
    var feed: List<Feed>,
    @SerializedName("items")
    
    var items: String,
    @SerializedName("relevance_score_definition")
    
    var relevanceScoreDefinition: String,
    @SerializedName("sentiment_score_definition")
    
    var sentimentScoreDefinition: String
)