package com.example.matchscoresapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matchscoresapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setupObserver()
        setContentView(binding.root)
    }

    private fun setupObserver() {

        viewModel.matches.observe(this) {
            it?.let {res ->
                Log.e("DATAS", res.data.toString())
            }
        }
        viewModel.error.observe(this) {
            it?.let {err ->
                Log.e("ERR", err)
            }
        }

    }
}