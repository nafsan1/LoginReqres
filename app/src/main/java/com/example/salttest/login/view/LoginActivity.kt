package com.example.salttest.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.salttest.data.main.view.MainActivity
import com.example.salttest.data.model.LoginRequest
import com.example.salttest.databinding.ActivityLoginBinding
import com.example.salttest.login.viewmodel.LoginViewModel
import com.example.salttest.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        setError()
        setLoading()
        setUi()
    }

    private fun setUi() = binding.apply {
        btnLogin.setOnClickListener {
           hideKeyboard()
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            if (username == "" || password == "") {
                Toast.makeText(
                    this@LoginActivity,
                    "Please fill username or password",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                getLogin(
                    username = username,
                    password = password
                )
            }
        }
    }

    private fun getLogin(username: String, password: String) = lifecycleScope.launch {
        viewModel.getLogin(LoginRequest(username, password))
    }

    private fun getData() = lifecycleScope.launch {
        viewModel.uiState.collect {
            it?.let {
                binding.edtPassword.text.clear()
                binding.edtUsername.text.clear()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setError() = lifecycleScope.launch {
        viewModel.errorState.collect {
            it?.let {
                Toast.makeText(this@LoginActivity, it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLoading()= lifecycleScope.launch {
        viewModel.loadingState.collect {
            it?.let {
                if (it){
                    binding.LinearLoading.visibility = View.VISIBLE
                }else{
                    binding.LinearLoading.visibility = View.GONE
                }
            }
        }
    }
}