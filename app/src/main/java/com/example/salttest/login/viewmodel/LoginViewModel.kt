package com.example.salttest.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salttest.data.model.ErrorResponse
import com.example.salttest.data.model.LoginRequest
import com.example.salttest.data.model.LoginResponse
import com.example.salttest.data.use_case.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: DataUseCase
) : ViewModel() {

    private var job: Job? = null

    private var _uiState = MutableStateFlow<LoginResponse?>(null)
    private var _errorState = MutableStateFlow<ErrorResponse?>(null)
    private var _loadingState = MutableStateFlow<Boolean?>(null)

    val uiState: StateFlow<LoginResponse?>
        get() = _uiState.asStateFlow()

    val errorState: StateFlow<ErrorResponse?>
        get() = _errorState.asStateFlow()

    val loadingState: StateFlow<Boolean?>
        get() = _loadingState.asStateFlow()

    fun getLogin(loginRequest: LoginRequest) {
        job = viewModelScope.launch {
            _loadingState.emit(true)
            useCase.getLogin.invoke(loginRequest).onSuccess {
                _uiState.emit(it)
                _loadingState.emit(false)
            }.onFailure {
                _loadingState.emit(false)
                _errorState.emit(ErrorResponse("User Not Found"))
                job?.cancel()

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        job = null
    }
}