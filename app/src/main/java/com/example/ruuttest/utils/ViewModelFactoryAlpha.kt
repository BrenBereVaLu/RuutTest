package com.example.ruuttest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.domain.usesCases.AlphaUseCaseImpl
import com.example.ruuttest.presentation.viewModels.BalanceSheetViewModel
import com.example.ruuttest.presentation.viewModels.IncomeStatementViewModel
import com.example.ruuttest.presentation.viewModels.NewsSentimentViewModel

class ViewModelFactoryAlpha(private val alphaCaseImpl: AlphaUseCaseImpl) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IncomeStatementViewModel::class.java)) {
            return IncomeStatementViewModel(alphaCaseImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryAlpha? = null
        fun getInstance(context: Context): ViewModelFactoryAlpha =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryAlpha(Injection.provideAlpha())
            }.also {
                instance = it
            }
    }
}

class ViewModelFactoryBalance(private val alphaCaseImpl: AlphaUseCaseImpl) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BalanceSheetViewModel::class.java)) {
            return BalanceSheetViewModel(alphaCaseImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryBalance? = null
        fun getInstance(context: Context): ViewModelFactoryBalance =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryBalance(Injection.provideAlpha())
            }.also {
                instance = it
            }
    }
}

class ViewModelFactoryNews(private val alphaCaseImpl: AlphaUseCaseImpl) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsSentimentViewModel::class.java)) {
            return NewsSentimentViewModel(alphaCaseImpl) as T
        }
        throw IllegalArgumentException("unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryNews? = null
        fun getInstance(context: Context): ViewModelFactoryNews =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryNews(Injection.provideAlpha())
            }.also {
                instance = it
            }
    }
}