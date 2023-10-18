package com.example.ruuttest.utils

import com.example.ruuttest.data.repositories.AlphaRepositoryImpl
import com.example.ruuttest.domain.usesCases.AlphaUseCaseImpl

object Injection {

    fun provideAlpha(): AlphaUseCaseImpl {
        val apiAlpha = RetrofitInstance.getApiAlpha()
        val alphaUseCase = AlphaRepositoryImpl(apiAlpha)
        return AlphaUseCaseImpl(alphaUseCase)
    }
}