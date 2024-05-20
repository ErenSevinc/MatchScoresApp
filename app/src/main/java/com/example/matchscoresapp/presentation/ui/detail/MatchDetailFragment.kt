package com.example.matchscoresapp.presentation.ui.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.matchscoresapp.R
import com.example.matchscoresapp.core.getAwayName
import com.example.matchscoresapp.core.getHomeName
import com.example.matchscoresapp.core.getLeagueName
import com.example.matchscoresapp.core.toDate
import com.example.matchscoresapp.databinding.FragmentMatchDetailBinding
import com.example.matchscoresapp.presentation.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {
    private val viewModel: MatchDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentMatchDetailBinding? = null
    private val args: MatchDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchDetailBinding.inflate(layoutInflater, container, false)

        mainViewModel.setToolbarTitle(getString(R.string.match_detail_title))
        mainViewModel.setToolbarVisibility(true)

        val league = args.matchItem.getLeagueName()
        val abbr = args.matchItem.matchAbbr
        val date = args.matchItem.date.toDate()
        val score = "${args.matchItem.homeMatchScore} - ${args.matchItem.awayMatchScore}"
        val homeName = args.matchItem.getHomeName()
        val awayName = args.matchItem.getAwayName()
        val halfTime = "(IY ${args.matchItem.homeHalfScore} - ${args.matchItem.awayHalfScore})"

        binding?.league?.text = league
        binding?.matchStatus?.text = abbr
        binding?.matchDate?.text = date
        binding?.score?.text = score
        binding?.homeTeam?.text = homeName
        binding?.awayTeam?.text = awayName
        binding?.halfTimeScore?.text = halfTime

        return binding?.root ?: View(context)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}