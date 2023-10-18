package com.example.ruuttest.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruuttest.domain.responses.errors.ErrorMessageResponse
import com.example.ruuttest.domain.responses.newsStatement.NewsSentimentResponse
import com.example.ruuttest.domain.usesCases.AlphaUseCaseImpl
import com.example.ruuttest.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSentimentViewModel @Inject constructor(
    private val alphaUseCase: AlphaUseCaseImpl
) : ViewModel() {

    private val _uiNewsState =
        MutableStateFlow(Resource.Success<NewsSentimentResponse>(null))
    val uiNewsState: StateFlow<Resource<NewsSentimentResponse>> =
        _uiNewsState

    private val _uiStateError = MutableStateFlow("")
    val uiStateError = _uiStateError.asStateFlow()

    private val _uiMessageError = MutableStateFlow(Resource.Error<ErrorMessageResponse>(""))
    val uiMessageError = _uiMessageError.asStateFlow()

    fun requestNews() {
        viewModelScope.launch {
            alphaUseCase.invokeNewsSentiment()
                .catch { error ->
                    _uiStateError.value = error.message.toString()
                    _uiMessageError.value = error.message?.let { Resource.Error(it) }!!
                }.collect { balance ->
                    _uiNewsState.value = Resource.Success(balance)
                }
        }
    }
}