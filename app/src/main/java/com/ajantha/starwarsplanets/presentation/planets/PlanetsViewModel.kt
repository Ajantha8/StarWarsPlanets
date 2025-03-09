package com.ajantha.starwarsplanets.presentation.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajantha.starwarsplanets.core.resource.ApiResult
import com.ajantha.starwarsplanets.core.resource.DispatcherProvider
import com.ajantha.starwarsplanets.domain.usecase.GetPlanetsUseCase
import com.ajantha.starwarsplanets.presentation.planets.state.PlanetsUiState
import com.ajantha.starwarsplanets.presentation.util.toMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState: MutableStateFlow<PlanetsUiState> = MutableStateFlow(PlanetsUiState())
    val uiState: StateFlow<PlanetsUiState> = _uiState.asStateFlow()

    init {
        getPlanets()
    }

    fun getPlanets() {
        viewModelScope.launch(dispatcherProvider.io) {
            getPlanetsUseCase().onEach { apiResult ->
                when (apiResult) {
                    is ApiResult.Loading -> {
                        _uiState.update {
                            it.copy(
                                loading = true
                            )
                        }
                    }
                    is ApiResult.Success -> {
                        _uiState.update {
                            it.copy(
                                planets = apiResult.data
                            )
                        }
                    }
                    is ApiResult.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = apiResult.exception.toMessage()
                            )
                        }
                    }
                    is ApiResult.Finally -> {
                        _uiState.update {
                            it.copy(
                                loading = false
                            )
                        }
                    }
                }
            }
                .launchIn(this)
        }
    }

}