package com.lfss.pgiapp.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfss.pgiapp.EventRepository
import com.lfss.pgiapp.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val eventRepository: EventRepository = EventRepository()

    private val _userState = MutableStateFlow<UserModel?>(null)
    val userState: StateFlow<UserModel?> = _userState

    fun userLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = eventRepository.userLogin(email, password)
                if (response.isSuccessful) { // API call worked, whether good or bad
                    response.body()?.let { body ->
                        _userState.value = body
                        Log.e("AAAAAAAAAAAAAAAAAAAAAAAA", "TESTEEEE: " + body.toString())
                    }
                } else {
                    _userState.value = null // Login failed
                }
            } catch (e: Exception) {
                _userState.value = null // Handle exception
            }
        }
    }
}