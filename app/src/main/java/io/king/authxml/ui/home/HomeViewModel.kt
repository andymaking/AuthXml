package io.king.authxml.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.king.authxml.data.network.Resource
import io.king.authxml.data.repository.AuthRepository
import io.king.authxml.data.repository.UserRepository
import io.king.authxml.data.responses.LoginResponse
import io.king.authxml.data.responses.User
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: UserRepository
): ViewModel(){

    private val _user : MutableLiveData<Resource<User>> = MutableLiveData()
    val user: LiveData<Resource<User>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}