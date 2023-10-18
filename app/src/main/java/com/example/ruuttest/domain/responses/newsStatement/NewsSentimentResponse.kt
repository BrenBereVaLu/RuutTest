package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NewsSentimentResponse(
    @SerializedName("feed")
    @Expose
    var feed: List<Feed>,
    @SerializedName("items")
    @Expose
    var items: String,
    @SerializedName("relevance_score_definition")
    @Expose
    var relevanceScoreDefinition: String,
    @SerializedName("sentiment_score_definition")
    @Expose
    var sentimentScoreDefinition: String
)