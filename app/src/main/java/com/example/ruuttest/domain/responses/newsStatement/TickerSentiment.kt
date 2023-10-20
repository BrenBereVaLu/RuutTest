package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TickerSentiment(
    @SerializedName("relevance_score")
    
    var relevanceScore: String,
    @SerializedName("ticker")
    
    var ticker: String,
    @SerializedName("ticker_sentiment_label")
    
    var tickerSentimentLabel: String,
    @SerializedName("ticker_sentiment_score")
    
    var tickerSentimentScore: String
)