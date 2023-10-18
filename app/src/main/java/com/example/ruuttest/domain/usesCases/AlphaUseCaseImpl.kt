package com.example.ruuttest.domain.usesCases

import com.example.ruuttest.data.repositories.AlphaRepository
import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlphaUseCaseImpl @Inject constructor(private val repository: AlphaRepository) :
    AlphaUseCase {

    override suspend fun invokeIncomeStatement(): Flow<IncomeStatementResponse> =
        repository.incomeStatementRepository()

    override suspend fun invokeBalanceSheet(): Flow<BalanceSheetResponse> =
        repository.balanceSheetRepository()

    override suspend fun invokeNewsSentiment(): Flow<NewsSentimentResponse> =
        repository.newsSentimentRepository()

}