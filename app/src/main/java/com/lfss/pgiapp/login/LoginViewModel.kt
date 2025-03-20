package com.lfss.pgiapp.login

import androidx.lifecycle.ViewModel
import com.lfss.pgiapp.EventRepository

class LoginViewModel : ViewModel(){
    val eventRepository: EventRepository = EventRepository()

    fun userLogin(userId: String, userPassword: String): Boolean{
        return eventRepository.authenticateUserLogin(userId, userPassword)
    }

}