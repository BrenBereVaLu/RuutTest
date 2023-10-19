package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

/**
 * I don't understand because you use "Expose" decorator.
 */
data class TickerSentiment(
    @SerializedName("relevance_score")
    @Expose
    var relevanceScore: String,
    @SerializedName("ticker")
    @Expose
    var ticker: String,
    @SerializedName("ticker_sentiment_label")
    @Expose
    var tickerSentimentLabel: String,
    @SerializedName("ticker_sentiment_score")
    @Expose
    var tickerSentimentScore: String
)