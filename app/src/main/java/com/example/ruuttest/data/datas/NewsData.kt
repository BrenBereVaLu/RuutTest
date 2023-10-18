package com.example.ruuttest.data.datas

data class NewsData(
    val title: String,
    val authors: String,
    val summary: String,
    val image: String,
    var isExpandable: Boolean = false
)