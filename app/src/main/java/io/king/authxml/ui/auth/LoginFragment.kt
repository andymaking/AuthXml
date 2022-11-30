package io.king.authxml.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import io.king.authxml.R
import io.king.authxml.databinding.FragmentLoginBinding
import io.king.authxml.data.network.AuthApi
import io.king.authxml.data.network.Resource
import io.king.authxml.data.repository.AuthRepository
import io.king.authxml.ui.base.BaseFragment
import io.king.authxml.ui.enable
import io.king.authxml.ui.home.HomeActivity
import io.king.authxml.ui.starttNewActivity
import io.king.authxml.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)
        binding.loginButton.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                        viewModel.saveAuthToken(it.value.token)
                        Toast.makeText(requireContext(), it.value.message, Toast.LENGTH_LONG).show()
                        requireActivity().starttNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    binding.progressBar.visible(false)
                    Toast.makeText(requireContext(), "Login failure", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading ->{
                    binding.progressBar.visible(true)
                }
            }
        })

        binding.loginPassword.addTextChangedListener {
            val email = binding.loginUsername.text.toString().trim()
            binding.loginButton.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginUsername.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            viewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()=
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}