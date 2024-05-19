package com.example.matchscoresapp.presentation.ui.list

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.matchscoresapp.core.LeagueUIState
import com.example.matchscoresapp.databinding.FragmentMatchListBinding
import com.example.matchscoresapp.domain.model.League
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
        setupObservers()

        return binding?.root ?: View(context)
    }

    private fun setupRecyclerView() {
        adapter = LeagueAdapter(
            onClick = {
                Log.e("Match", it.toString())
                val direction = MatchListFragmentDirections.navigateToMatchDetail(it)
                findNavController().navigate(direction)
            },
            onFavClick = {
                if (it.isFavourite) {
                    viewModel.insertMatch(it)
                } else {
                    viewModel.deleteMatch(it)
                }
            }
        )
        binding?.list?.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.matchesState.observe(viewLifecycleOwner) {
            when(it) {
                is LeagueUIState.Error -> {
                    errorTextVisibility(it.errorMessage)
                }
                is LeagueUIState.Loading -> {
                    loaderVisibility(it.isLoading)
                }
                is LeagueUIState.LeagueList -> {
                    recyclerViewVisibility(it.data)
                    Log.e("DATAS", it.data.toString())
                }
            }
        }
    }

    private fun errorTextVisibility(errorMessage: String) {
        binding?.apply {
            loading.isVisible = false
            list.isVisible = false
            errorText.isVisible = true

            errorText.text = errorMessage
        }
    }

    private fun loaderVisibility(isLoader: Boolean) {
        binding?.apply {
            loading.isVisible = isLoader
            list.isVisible = !isLoader
            errorText.isVisible = !isLoader
        }
    }

    private fun recyclerViewVisibility(leagueList: List<League>) {
        binding?.apply {
            loading.isVisible = false
            list.isVisible = true
            errorText.isVisible = false

            setupRecyclerView()
            adapter?.submitList(leagueList.toMutableList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        adapter = null
    }
}