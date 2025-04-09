package com.example.comprasapp.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.comprasapp.R
import com.example.comprasapp.databinding.FragmentLoginBinding
import com.example.comprasapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loginButton.setOnClickListener {
            val user = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            viewModel.login(user, pass)
        }
        viewModel.loginState.observe(viewLifecycleOwner) { loginSuccess ->
            if (loginSuccess) {
                findNavController().navigate(R.id.action_loginFragment_to_countryFragment)
            } else {
                Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }

        }
    }
}