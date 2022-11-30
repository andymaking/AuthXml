package io.king.authxml.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import io.king.authxml.R
import io.king.authxml.databinding.FragmentLoginBinding
import io.king.authxml.data.network.AuthApi
import io.king.authxml.data.network.Resource
import io.king.authxml.data.repository.AuthRepository
import io.king.authxml.ui.base.BaseFragment

class RegisterFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}