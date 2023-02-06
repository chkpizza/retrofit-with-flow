package com.antique.flow_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.antique.flow_mvvm.databinding.ActivityMainBinding
import com.antique.flow_mvvm.model.ApiStatus
import com.antique.flow_mvvm.viewmodel.MainViewModel
import com.antique.flow_mvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        test()
    }

    private fun test() {
        viewModel.daily.asLiveData().observe(this) {
            when(it) {
                is ApiStatus.Success -> {
                    binding.loadingProgressView.visibility = View.GONE
                    binding.logView.text = it.toString()
                }
                is ApiStatus.Error -> {
                    binding.loadingProgressView.visibility = View.GONE
                    binding.logView.text = it.errorMessage
                }
                is ApiStatus.Loading -> {
                    binding.loadingProgressView.visibility = View.VISIBLE
                }
            }
        }
        viewModel.load()
    }
}