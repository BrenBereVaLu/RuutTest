package com.example.ruuttest.domain.responses.incomeStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class IncomeStatementResponse(
    @SerializedName("annualReports")
    
    var annualReports: List<AnnualReport>,
    @SerializedName("quarterlyReports")
    
    var quarterlyReports: List<QuarterlyReport>,
    @SerializedName("symbol")
    
    var symbol: String
)