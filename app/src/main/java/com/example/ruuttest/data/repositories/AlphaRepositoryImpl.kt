package com.example.ruuttest.data.repositories

import com.example.ruuttest.data.requests.AlphaRequest
import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlphaRepositoryImpl @Inject constructor(private val api: AlphaRequest) :
    AlphaRepository {

    override suspend fun incomeStatementRepository(): Flow<IncomeStatementResponse> = flow {
        val result = api.requestIncomeStatement()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun balanceSheetRepository(): Flow<BalanceSheetResponse> = flow {
        val result = api.requestBalanceSheet()
        emit(result)
    }.flowOn(Dispatchers.IO)


    override suspend fun newsSentimentRepository(): Flow<NewsSentimentResponse> = flow {
        val result = api.requestNewsStatement()
        emit(result)
    }.flowOn(Dispatchers.IO)
}