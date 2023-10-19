package com.example.ruuttest.domain.responses.newsStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

/**
 * I don't understand because you use "Expose" decorator.
 */
data class Feed(
    @SerializedName("authors")
    @Expose
    var authors: List<String>,
    @SerializedName("banner_image")
    @Expose
    var bannerImage: String,
    @SerializedName("category_within_source")
    @Expose
    var categoryWithinSource: String,
    @SerializedName("overall_sentiment_label")
    @Expose
    var overallSentimentLabel: String,
    @SerializedName("overall_sentiment_score")
    @Expose
    var overallSentimentScore: Double,
    @SerializedName("source")
    @Expose
    var source: String,
    @SerializedName("source_domain")
    @Expose
    var sourceDomain: String,
    @SerializedName("summary")
    @Expose
    var summary: String,
    @SerializedName("ticker_sentiment")
    @Expose
    var tickerSentiment: List<TickerSentiment>,
    @SerializedName("time_published")
    @Expose
    var timePublished: String,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("topics")
    @Expose
    var topics: List<Topic>,
    @SerializedName("url")
    @Expose
    var url: String
)