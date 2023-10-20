package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Feed(
    @SerializedName("authors")
    
    var authors: List<String>,
    @SerializedName("banner_image")
    
    var bannerImage: String,
    @SerializedName("category_within_source")
    
    var categoryWithinSource: String,
    @SerializedName("overall_sentiment_label")
    
    var overallSentimentLabel: String,
    @SerializedName("overall_sentiment_score")
    
    var overallSentimentScore: Double,
    @SerializedName("source")
    
    var source: String,
    @SerializedName("source_domain")
    
    var sourceDomain: String,
    @SerializedName("summary")
    
    var summary: String,
    @SerializedName("ticker_sentiment")
    
    var tickerSentiment: List<TickerSentiment>,
    @SerializedName("time_published")
    
    var timePublished: String,
    @SerializedName("title")
    
    var title: String,
    @SerializedName("topics")
    
    var topics: List<Topic>,
    @SerializedName("url")
    
    var url: String
)