package com.lfss.pgiapp.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lfss.pgiapp.MainActivity
import com.lfss.pgiapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstaceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            userLogin(
                binding.userIdContent.text.toString(),
                binding.passwordContent.text.toString()
            )
        }

        lifecycleScope.launch {
            viewModel.userState.collectLatest { user ->
                if (user != null) {
                    Toast.makeText(this@LoginFragment.activity, "Welcome, ${user.username}!", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(
                            requireContext(), MainActivity::class.java).apply {
                                putExtra("userId", user.userId)
                        }
                    )
                } else {
                    Toast.makeText(this@LoginFragment.activity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun userLogin(email: String, password: String) {
        //TODO: remove this later
        viewModel.userLogin("test1@test.com", "test123")
    }
}