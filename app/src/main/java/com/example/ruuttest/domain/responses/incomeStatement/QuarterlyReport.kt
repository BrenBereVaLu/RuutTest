package com.example.ruuttest.domain.responses.incomeStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

/**
 * I don't understand because you use "Expose" decorator.
 */
data class QuarterlyReport(
    @SerializedName("comprehensiveIncomeNetOfTax")
    @Expose
    var comprehensiveIncomeNetOfTax: String,
    @SerializedName("costOfRevenue")
    @Expose
    var costOfRevenue: String,
    @SerializedName("costofGoodsAndServicesSold")
    @Expose
    var costofGoodsAndServicesSold: String,
    @SerializedName("depreciation")
    @Expose
    var depreciation: String,
    @SerializedName("depreciationAndAmortization")
    @Expose
    var depreciationAndAmortization: String,
    @SerializedName("ebit")
    @Expose
    var ebit: String,
    @SerializedName("ebitda")
    @Expose
    var ebitda: String,
    @SerializedName("fiscalDateEnding")
    @Expose
    var fiscalDateEnding: String,
    @SerializedName("grossProfit")
    @Expose
    var grossProfit: String,
    @SerializedName("incomeBeforeTax")
    @Expose
    var incomeBeforeTax: String,
    @SerializedName("incomeTaxExpense")
    @Expose
    var incomeTaxExpense: String,
    @SerializedName("interestAndDebtExpense")
    @Expose
    var interestAndDebtExpense: String,
    @SerializedName("interestExpense")
    @Expose
    var interestExpense: String,
    @SerializedName("interestIncome")
    @Expose
    var interestIncome: String,
    @SerializedName("investmentIncomeNet")
    @Expose
    var investmentIncomeNet: String,
    @SerializedName("netIncome")
    @Expose
    var netIncome: String,
    @SerializedName("netIncomeFromContinuingOperations")
    @Expose
    var netIncomeFromContinuingOperations: String,
    @SerializedName("netInterestIncome")
    @Expose
    var netInterestIncome: String,
    @SerializedName("nonInterestIncome")
    @Expose
    var nonInterestIncome: String,
    @SerializedName("operatingExpenses")
    @Expose
    var operatingExpenses: String,
    @SerializedName("operatingIncome")
    @Expose
    var operatingIncome: String,
    @SerializedName("otherNonOperatingIncome")
    @Expose
    var otherNonOperatingIncome: String,
    @SerializedName("reportedCurrency")
    @Expose
    var reportedCurrency: String,
    @SerializedName("researchAndDevelopment")
    @Expose
    var researchAndDevelopment: String,
    @SerializedName("sellingGeneralAndAdministrative")
    @Expose
    var sellingGeneralAndAdministrative: String,
    @SerializedName("totalRevenue")
    @Expose
    var totalRevenue: String
)