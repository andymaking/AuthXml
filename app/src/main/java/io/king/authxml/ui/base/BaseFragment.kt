package io.king.authxml.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import io.king.authxml.data.UserPreferences
import io.king.authxml.data.network.RemoteDataSource
import io.king.authxml.data.repository.BaseRepository
import io.king.authxml.data.responses.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment <VM: ViewModel, B: ViewBinding, R: BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel : VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch {
            userPreferences.authToken.first()
        }
        return binding.root
    }

    abstract fun getViewModel() : Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun getFragmentRepository() : R
}