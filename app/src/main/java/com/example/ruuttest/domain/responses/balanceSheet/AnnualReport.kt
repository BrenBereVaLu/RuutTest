package com.example.ruuttest.domain.responses.balanceSheet


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

/**
 * I don't understand because you use "Expose" decorator.
 */
data class AnnualReport(
    @SerializedName("accumulatedDepreciationAmortizationPPE")
    @Expose
    var accumulatedDepreciationAmortizationPPE: String,
    @SerializedName("capitalLeaseObligations")
    @Expose
    var capitalLeaseObligations: String,
    @SerializedName("cashAndCashEquivalentsAtCarryingValue")
    @Expose
    var cashAndCashEquivalentsAtCarryingValue: String,
    @SerializedName("cashAndShortTermInvestments")
    @Expose
    var cashAndShortTermInvestments: String,
    @SerializedName("commonStock")
    @Expose
    var commonStock: String,
    @SerializedName("commonStockSharesOutstanding")
    @Expose
    var commonStockSharesOutstanding: String,
    @SerializedName("currentAccountsPayable")
    @Expose
    var currentAccountsPayable: String,
    @SerializedName("currentDebt")
    @Expose
    var currentDebt: String,
    @SerializedName("currentLongTermDebt")
    @Expose
    var currentLongTermDebt: String,
    @SerializedName("currentNetReceivables")
    @Expose
    var currentNetReceivables: String,
    @SerializedName("deferredRevenue")
    @Expose
    var deferredRevenue: String,
    @SerializedName("fiscalDateEnding")
    @Expose
    var fiscalDateEnding: String,
    @SerializedName("goodwill")
    @Expose
    var goodwill: String,
    @SerializedName("intangibleAssets")
    @Expose
    var intangibleAssets: String,
    @SerializedName("intangibleAssetsExcludingGoodwill")
    @Expose
    var intangibleAssetsExcludingGoodwill: String,
    @SerializedName("inventory")
    @Expose
    var inventory: String,
    @SerializedName("investments")
    @Expose
    var investments: String,
    @SerializedName("longTermDebt")
    @Expose
    var longTermDebt: String,
    @SerializedName("longTermDebtNoncurrent")
    @Expose
    var longTermDebtNoncurrent: String,
    @SerializedName("longTermInvestments")
    @Expose
    var longTermInvestments: String,
    @SerializedName("otherCurrentAssets")
    @Expose
    var otherCurrentAssets: String,
    @SerializedName("otherCurrentLiabilities")
    @Expose
    var otherCurrentLiabilities: String,
    @SerializedName("otherNonCurrentAssets")
    @Expose
    var otherNonCurrentAssets: String,
    @SerializedName("otherNonCurrentLiabilities")
    @Expose
    var otherNonCurrentLiabilities: String,
    @SerializedName("propertyPlantEquipment")
    @Expose
    var propertyPlantEquipment: String,
    @SerializedName("reportedCurrency")
    @Expose
    var reportedCurrency: String,
    @SerializedName("retainedEarnings")
    @Expose
    var retainedEarnings: String,
    @SerializedName("shortLongTermDebtTotal")
    @Expose
    var shortLongTermDebtTotal: String,
    @SerializedName("shortTermDebt")
    @Expose
    var shortTermDebt: String,
    @SerializedName("shortTermInvestments")
    @Expose
    var shortTermInvestments: String,
    @SerializedName("totalAssets")
    @Expose
    var totalAssets: String,
    @SerializedName("totalCurrentAssets")
    @Expose
    var totalCurrentAssets: String,
    @SerializedName("totalCurrentLiabilities")
    @Expose
    var totalCurrentLiabilities: String,
    @SerializedName("totalLiabilities")
    @Expose
    var totalLiabilities: String,
    @SerializedName("totalNonCurrentAssets")
    @Expose
    var totalNonCurrentAssets: String,
    @SerializedName("totalNonCurrentLiabilities")
    @Expose
    var totalNonCurrentLiabilities: String,
    @SerializedName("totalShareholderEquity")
    @Expose
    var totalShareholderEquity: String,
    @SerializedName("treasuryStock")
    @Expose
    var treasuryStock: String
)