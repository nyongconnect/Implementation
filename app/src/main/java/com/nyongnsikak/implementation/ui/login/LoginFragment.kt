package com.nyongnsikak.implementation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.nyongnsikak.implementation.R
import com.nyongnsikak.implementation.databinding.FragmentLoginBinding
import com.nyongnsikak.implementation.ui.login.viewmodel.ProfileViewModel
import com.nyongnsikak.implementation.utils.Result
import com.nyongnsikak.movieviewer.ui.zbase.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

   private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signout.setOnClickListener{
            viewModel.logout()
        }
        binding.facebookLogin.setOnClickListener{
            viewModel.login()
        }
        viewModel.loginCallback()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun observeData() {
        super.observeData()
        viewModel.user.observe(viewLifecycleOwner, Observer {result ->
            when(result){

                is Result.Success->{
                    binding.name.text = result.data?.name


                }
            }

        })
        viewModel.logout.observe(viewLifecycleOwner, Observer {
            result-> when(result){
            is Result.Success -> {Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show()}
        }
        })
    }

}