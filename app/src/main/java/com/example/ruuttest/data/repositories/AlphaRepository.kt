package com.example.ruuttest.data.repositories

import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import kotlinx.coroutines.flow.Flow

interface AlphaRepository {

    suspend fun incomeStatementRepository(): Flow<IncomeStatementResponse>
    suspend fun balanceSheetRepository(): Flow<BalanceSheetResponse>
    suspend fun newsSentimentRepository(): Flow<NewsSentimentResponse>
}