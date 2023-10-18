package com.example.ruuttest.domain.usesCases

import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import kotlinx.coroutines.flow.Flow

interface AlphaUseCase {

    suspend fun invokeIncomeStatement(): Flow<IncomeStatementResponse>
    suspend fun invokeBalanceSheet(): Flow<BalanceSheetResponse>
    suspend fun invokeNewsSentiment(): Flow<NewsSentimentResponse>
}