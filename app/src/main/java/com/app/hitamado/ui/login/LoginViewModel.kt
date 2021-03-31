package com.app.hitamado.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.hitamado.data.source.SjtRepository
import com.app.hitamado.model.login.ResponseAccount

class LoginViewModel@ViewModelInject constructor(private val sjtRepository: SjtRepository) :
    ViewModel() {
    fun doLogin(email:String,password:String):LiveData<ResponseAccount> = sjtRepository.getResponseLogin(email, password)
}