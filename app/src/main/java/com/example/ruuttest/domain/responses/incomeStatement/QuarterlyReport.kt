package com.example.ruuttest.domain.responses.incomeStatement


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class QuarterlyReport(
    @SerializedName("comprehensiveIncomeNetOfTax")
    
    var comprehensiveIncomeNetOfTax: String,
    @SerializedName("costOfRevenue")
    
    var costOfRevenue: String,
    @SerializedName("costofGoodsAndServicesSold")
    
    var costofGoodsAndServicesSold: String,
    @SerializedName("depreciation")
    
    var depreciation: String,
    @SerializedName("depreciationAndAmortization")
    
    var depreciationAndAmortization: String,
    @SerializedName("ebit")
    
    var ebit: String,
    @SerializedName("ebitda")
    
    var ebitda: String,
    @SerializedName("fiscalDateEnding")
    
    var fiscalDateEnding: String,
    @SerializedName("grossProfit")
    
    var grossProfit: String,
    @SerializedName("incomeBeforeTax")
    
    var incomeBeforeTax: String,
    @SerializedName("incomeTaxExpense")
    
    var incomeTaxExpense: String,
    @SerializedName("interestAndDebtExpense")
    
    var interestAndDebtExpense: String,
    @SerializedName("interestExpense")
    
    var interestExpense: String,
    @SerializedName("interestIncome")
    
    var interestIncome: String,
    @SerializedName("investmentIncomeNet")
    
    var investmentIncomeNet: String,
    @SerializedName("netIncome")
    
    var netIncome: String,
    @SerializedName("netIncomeFromContinuingOperations")
    
    var netIncomeFromContinuingOperations: String,
    @SerializedName("netInterestIncome")
    
    var netInterestIncome: String,
    @SerializedName("nonInterestIncome")
    
    var nonInterestIncome: String,
    @SerializedName("operatingExpenses")
    
    var operatingExpenses: String,
    @SerializedName("operatingIncome")
    
    var operatingIncome: String,
    @SerializedName("otherNonOperatingIncome")
    
    var otherNonOperatingIncome: String,
    @SerializedName("reportedCurrency")
    
    var reportedCurrency: String,
    @SerializedName("researchAndDevelopment")
    
    var researchAndDevelopment: String,
    @SerializedName("sellingGeneralAndAdministrative")
    
    var sellingGeneralAndAdministrative: String,
    @SerializedName("totalRevenue")
    
    var totalRevenue: String
)