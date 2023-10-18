package com.example.ruuttest.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruuttest.domain.responses.balanceSheet.BalanceSheetResponse
import com.example.ruuttest.domain.responses.errors.ErrorMessageResponse
import com.example.ruuttest.domain.usesCases.AlphaUseCaseImpl
import com.example.ruuttest.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class BalanceSheetViewModel @Inject constructor(
    private val alphaUseCase: AlphaUseCaseImpl
) : ViewModel() {

    private val _uiBalanceState =
        MutableStateFlow(Resource.Success<BalanceSheetResponse>(null))
    val uiBalanceState: StateFlow<Resource<BalanceSheetResponse>> =
        _uiBalanceState

    private val _uiStateError = MutableStateFlow("")
    val uiStateError = _uiStateError.asStateFlow()

    private val _uiMessageError = MutableStateFlow(Resource.Error<ErrorMessageResponse>(""))
    val uiMessageError = _uiMessageError.asStateFlow()

    fun requestBalance() {
        viewModelScope.launch {
            alphaUseCase.invokeBalanceSheet()
                .catch { error ->
                    _uiStateError.value = error.message.toString()
                    _uiMessageError.value = error.message?.let { Resource.Error(it) }!!
                }.collect { balance ->
                    _uiBalanceState.value = Resource.Success(balance)
                }
        }
    }
}