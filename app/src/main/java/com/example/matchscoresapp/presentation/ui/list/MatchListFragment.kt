package com.example.matchscoresapp.presentation.ui.list

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.matchscoresapp.databinding.FragmentMatchListBinding
import com.example.matchscoresapp.presentation.adapter.LeagueAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListFragment : Fragment() {

    private val viewModel: MatchListViewModel by viewModels()
    private var binding: FragmentMatchListBinding? = null
    private var adapter: LeagueAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchListBinding.inflate(layoutInflater, container, false)

        viewModel.getMatches()
        setupLayout()
        setupObservers()

        return binding?.root ?: View(context)
    }

    private fun setupLayout() {
        adapter = LeagueAdapter{
            Log.e("Match", it.toString())
            val direction = MatchListFragmentDirections.navigateToMatchDetail(it)
            findNavController().navigate(direction)
        }
        binding?.list?.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.matches.observe(viewLifecycleOwner) {
            it?.let { res ->
                Log.e("DATAS", res.toString())
                adapter?.submitList(it)

            }
            viewModel.error.observe(viewLifecycleOwner) {
                it?.let { err ->
                    Log.e("ERR", err)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        adapter = null
    }
}