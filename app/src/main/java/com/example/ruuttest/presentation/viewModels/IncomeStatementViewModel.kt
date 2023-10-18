package com.example.ruuttest.presentation.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruuttest.domain.responses.errors.ErrorMessageResponse
import com.example.ruuttest.domain.responses.incomeStatement.IncomeStatementResponse
import com.example.ruuttest.domain.usesCases.AlphaUseCaseImpl
import com.example.ruuttest.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class IncomeStatementViewModel @Inject constructor(
    private val alphaUseCase: AlphaUseCaseImpl
) : ViewModel() {

    private val _uiIncomeState =
        MutableStateFlow(Resource.Success<IncomeStatementResponse>(null))
    val uiIncomeState: StateFlow<Resource<IncomeStatementResponse>> =
        _uiIncomeState

    private val _uiStateError = MutableStateFlow("")
    val uiStateError = _uiStateError.asStateFlow()

    private val _uiMessageError = MutableStateFlow(Resource.Error<ErrorMessageResponse>(""))
    val uiMessageError = _uiMessageError.asStateFlow()

    fun requestIncome() {
        viewModelScope.launch {
            alphaUseCase.invokeIncomeStatement()
                .catch { error ->
                    _uiStateError.value = error.message.toString()
                    _uiMessageError.value = error.message?.let { Resource.Error(it) }!!
                }.collect { income ->
                    _uiIncomeState.value = Resource.Success(income)
                }
        }
    }
}