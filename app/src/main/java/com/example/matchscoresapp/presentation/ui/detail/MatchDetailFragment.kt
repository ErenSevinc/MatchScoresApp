package com.example.matchscoresapp.presentation.ui.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.matchscoresapp.databinding.FragmentMatchDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {
    private val viewModel: MatchDetailViewModel by viewModels()
    private var binding: FragmentMatchDetailBinding? = null
    private val args: MatchDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchDetailBinding.inflate(layoutInflater, container, false)

        val abbr = args.matchItem.matchAbbr
        val date = args.matchItem.date.toString()
        val score = "${args.matchItem.homeMatchScore} - ${args.matchItem.awayMatchScore}"
        val homeName = args.matchItem.homeName
        val awayName = args.matchItem.awayName

        binding?.matchStatus?.text = abbr
        binding?.matchDate?.text = date
        binding?.score?.text = score
        binding?.homeTeam?.text = homeName
        binding?.awayTeam?.text = awayName

        return binding?.root ?: View(context)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}