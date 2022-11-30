package io.king.authxml.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import io.king.authxml.R
import io.king.authxml.data.network.Resource
import io.king.authxml.data.network.UserApi
import io.king.authxml.data.repository.AuthRepository
import io.king.authxml.data.repository.UserRepository
import io.king.authxml.data.responses.User
import io.king.authxml.databinding.FragmentHomeBinding
import io.king.authxml.databinding.FragmentLoginBinding
import io.king.authxml.ui.auth.AuthViewModel
import io.king.authxml.ui.base.BaseFragment
import io.king.authxml.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    binding.progressBar.visible(false)
                    updateUi(it.value)
                }
                is Resource.Loading ->{
                    binding.progressBar.visible(true)
                }
                is Resource.Failure ->{
                    binding.progressBar.visible(false)

                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(user: User){
        with(binding){
            textViewId.text = user.id.toString()
            textViewEmail.text = user.email.toString()
            textViewName.text = user.first_name.toString()+ " " +user.last_name.toString()
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() : UserRepository{
        val token = runBlocking { userPreferences.authToken.first() }
        val  api  = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }


}