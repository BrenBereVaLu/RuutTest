package com.example.ruuttest.data.requests

import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import retrofit2.http.GET

/**
 * Remove the API key from here! It's very dangerous, especially if your repository is public.
 */
interface AlphaRequest {

    //INCOME_STATEMENT
    @GET("query?function=INCOME_STATEMENT&symbol=IBM&apikey=S5DU9F6BCRXC047Q")
    suspend fun requestIncomeStatement(): IncomeStatementResponse
    //BALANCE_SHEET
    @GET("query?function=BALANCE_SHEET&symbol=IBM&apikey=S5DU9F6BCRXC047Q")
    suspend fun requestBalanceSheet(): BalanceSheetResponse
    //NEWS_SENTIMENT
    @GET("query?function=NEWS_SENTIMENT&topics=technology&apikey=S5DU9F6BCRXC047Q")
    suspend fun requestNewsStatement(): NewsSentimentResponse
}