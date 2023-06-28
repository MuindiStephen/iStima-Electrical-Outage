package com.example.istima.viewmodels

import androidx.lifecycle.ViewModel
import com.example.istima.data.GoogleSignInResult
import com.example.istima.utils.GoogleSignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GoogleSIgnInViewModel {

    private val _state = MutableStateFlow(GoogleSignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: GoogleSignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { GoogleSignInState() }
    }
}