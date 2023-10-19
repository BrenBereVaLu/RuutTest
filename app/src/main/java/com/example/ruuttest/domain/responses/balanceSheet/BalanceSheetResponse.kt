package com.example.ruuttest.domain.responses.balanceSheet


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
/**
 * I don't understand because you use "Expose" decorator.
 */
data class BalanceSheetResponse(
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