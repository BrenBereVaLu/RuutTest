package com.example.ruuttest.domain.responses.incomeStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class IncomeStatementResponse(
    @SerializedName("annualReports")
    @Expose
    var annualReports: List<AnnualReport>,
    @SerializedName("quarterlyReports")
    @Expose
    var quarterlyReports: List<QuarterlyReport>,
    @SerializedName("symbol")
    @Expose
    var symbol: String
)