package com.example.ruuttest.domain.responses.balanceSheet


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class BalanceSheetResponse(
    @SerializedName("annualReports")
    
    var annualReports: List<AnnualReport>,
    @SerializedName("quarterlyReports")
    
    var quarterlyReports: List<QuarterlyReport>,
    @SerializedName("symbol")
    
    var symbol: String
)