package io.king.authxml.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.king.authxml.data.repository.AuthRepository
import io.king.authxml.data.repository.BaseRepository
import io.king.authxml.data.repository.UserRepository
import io.king.authxml.ui.auth.AuthViewModel
import io.king.authxml.ui.home.HomeViewModel

class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}