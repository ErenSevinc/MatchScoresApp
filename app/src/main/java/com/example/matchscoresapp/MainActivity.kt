package com.example.matchscoresapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.matchscoresapp.databinding.ActivityMainBinding
import com.example.matchscoresapp.presentation.adapter.LeagueAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { LeagueAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.getMatches()
        setupObserver()
        setContentView(binding.root)
    }

    private fun setupObserver() {

        viewModel.matches.observe(this) {
            it?.let { res ->
                Log.e("DATAS", res.toString())
                binding.list.adapter = adapter
                adapter.submitList(it)

            }
            viewModel.error.observe(this) {
                it?.let { err ->
                    Log.e("ERR", err)
                }
            }

        }
    }
}