package com.example.ruuttest.domain.responses.balanceSheet


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class QuarterlyReport(
    @SerializedName("accumulatedDepreciationAmortizationPPE")
    
    var accumulatedDepreciationAmortizationPPE: String,
    @SerializedName("capitalLeaseObligations")
    
    var capitalLeaseObligations: String,
    @SerializedName("cashAndCashEquivalentsAtCarryingValue")
    
    var cashAndCashEquivalentsAtCarryingValue: String,
    @SerializedName("cashAndShortTermInvestments")
    
    var cashAndShortTermInvestments: String,
    @SerializedName("commonStock")
    
    var commonStock: String,
    @SerializedName("commonStockSharesOutstanding")
    
    var commonStockSharesOutstanding: String,
    @SerializedName("currentAccountsPayable")
    
    var currentAccountsPayable: String,
    @SerializedName("currentDebt")
    
    var currentDebt: String,
    @SerializedName("currentLongTermDebt")
    
    var currentLongTermDebt: String,
    @SerializedName("currentNetReceivables")
    
    var currentNetReceivables: String,
    @SerializedName("deferredRevenue")
    
    var deferredRevenue: String,
    @SerializedName("fiscalDateEnding")
    
    var fiscalDateEnding: String,
    @SerializedName("goodwill")
    
    var goodwill: String,
    @SerializedName("intangibleAssets")
    
    var intangibleAssets: String,
    @SerializedName("intangibleAssetsExcludingGoodwill")
    
    var intangibleAssetsExcludingGoodwill: String,
    @SerializedName("inventory")
    
    var inventory: String,
    @SerializedName("investments")
    
    var investments: String,
    @SerializedName("longTermDebt")
    
    var longTermDebt: String,
    @SerializedName("longTermDebtNoncurrent")
    
    var longTermDebtNoncurrent: String,
    @SerializedName("longTermInvestments")
    
    var longTermInvestments: String,
    @SerializedName("otherCurrentAssets")
    
    var otherCurrentAssets: String,
    @SerializedName("otherCurrentLiabilities")
    
    var otherCurrentLiabilities: String,
    @SerializedName("otherNonCurrentAssets")
    
    var otherNonCurrentAssets: String,
    @SerializedName("otherNonCurrentLiabilities")
    
    var otherNonCurrentLiabilities: String,
    @SerializedName("propertyPlantEquipment")
    
    var propertyPlantEquipment: String,
    @SerializedName("reportedCurrency")
    
    var reportedCurrency: String,
    @SerializedName("retainedEarnings")
    
    var retainedEarnings: String,
    @SerializedName("shortLongTermDebtTotal")
    
    var shortLongTermDebtTotal: String,
    @SerializedName("shortTermDebt")
    
    var shortTermDebt: String,
    @SerializedName("shortTermInvestments")
    
    var shortTermInvestments: String,
    @SerializedName("totalAssets")
    
    var totalAssets: String,
    @SerializedName("totalCurrentAssets")
    
    var totalCurrentAssets: String,
    @SerializedName("totalCurrentLiabilities")
    
    var totalCurrentLiabilities: String,
    @SerializedName("totalLiabilities")
    
    var totalLiabilities: String,
    @SerializedName("totalNonCurrentAssets")
    
    var totalNonCurrentAssets: String,
    @SerializedName("totalNonCurrentLiabilities")
    
    var totalNonCurrentLiabilities: String,
    @SerializedName("totalShareholderEquity")
    
    var totalShareholderEquity: String,
    @SerializedName("treasuryStock")
    
    var treasuryStock: String
)